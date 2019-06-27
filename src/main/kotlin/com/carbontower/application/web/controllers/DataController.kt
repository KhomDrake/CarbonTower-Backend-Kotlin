package com.carbontower.application.web.controllers

import com.carbontower.application.web.*
import com.carbontower.domain.entities.application.LogApplicationDb
import com.carbontower.domain.entities.database.T_LOGS_SERVER
import com.carbontower.domain.entities.response.UserData
import com.carbontower.domain.services.data.DataService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DataController(private val dataService: DataService, private val cookie: Cookie) {
    fun routes() {
        path("/data") {
            get("validate", toJson { validate(it) })
            get("role", toJson { getRole(it) })
            get("idrole-empresa", toJson { getIdRole(it, Role.Empresa) })
            get("idrole-jogador", toJson { getIdRole(it, Role.Jogador) })
            get("idrole-administrador", toJson { getIdRole(it, Role.Administrador) })
            get("user", toJson { getUserData(it) })
            get("logs", toJson { returnLogs(it) })
        }
    }

    private fun returnLogs(ctx: Context) : List<LogApplicationDb> {
        val logs = mutableListOf<LogApplicationDb>()

        transaction {
            val logsDb = T_LOGS_SERVER.selectAll().orderBy(T_LOGS_SERVER.idLogsServer, isAsc = false)

            logsDb.forEach {
                logs.add(LogApplicationDb(it[T_LOGS_SERVER.router], it[T_LOGS_SERVER.statusCode], it[T_LOGS_SERVER.message],
                    it[T_LOGS_SERVER.dateTime], it[T_LOGS_SERVER.method], it[T_LOGS_SERVER.server]))
            }
        }

        return logs.toList()
    }

    private fun getUserData(ctx: Context) : UserData {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val userData = dataService.getUserData(idUser)
        ctx.insertLogSuccess("Dados do usuário pegos com sucesso $userData")
        return userData
    }

    private fun validate(ctx: Context) : Boolean {
        val c: String? = ctx.cookie(cookie.cookieName)
        val result = if(c.isNullOrEmpty()) false else cookie.contains(c)
        ctx.insertLogSuccess("Usuário está válido para acessar, está logado: $result")
        return result
    }

    private fun getIdRole(ctx: Context, role: Role) : Int {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idRole = dataService.getIdRole(idUser, role)
        ctx.insertLogSuccess("Usuário $idUser possui o id role-$role: $idRole")
        return idRole
    }

    private fun getRole(ctx: Context) : String {
        ctx.validateCookie(cookie)
        var role: String = "nenhum"
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        if(idUser.count() == 11)  role = "jogador"
        else if(idUser.count() == 14) role = "empresa"
        ctx.insertLogSuccess("Usuário: $idUser possui o papel: $role")
        return role
    }
}