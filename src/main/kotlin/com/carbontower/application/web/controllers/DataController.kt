package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.Role
import com.carbontower.application.web.toJson
import com.carbontower.domain.entities.response.UserData
import com.carbontower.domain.services.data.DataService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class DataController(private val dataService: DataService, private val cookie: Cookie) {
    fun routes() {
        path("/data") {
            get("validate", toJson { validate(it) })
            get("role", toJson { getRole(it) })
            get("idrole-empresa", toJson { getIdRole(it, Role.Empresa) })
            get("idrole-jogador", toJson { getIdRole(it, Role.Jogador) })
            get("idrole-administrador", toJson { getIdRole(it, Role.Administrador) })
            get("user", toJson { getUserData(it) })
        }
    }

    private fun getUserData(ctx: Context) : UserData {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        return dataService.getUserData(idUser)
    }

    private fun validate(ctx: Context) : Boolean {
        val c: String? = ctx.cookie(cookie.cookieName)
        if(c.isNullOrEmpty()) return false
        return cookie.contains(c)
    }

    private fun getIdRole(ctx: Context, role: Role) : Int {
        var idRole = 0
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        idRole = dataService.getIdRole(idUser, role)
        return idRole
    }

    private fun getRole(ctx: Context) : String {
        var role: String = "nenhum"
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        role = if(idUser.count() == 11) "jogador" else "empresa"
        return role
    }
}