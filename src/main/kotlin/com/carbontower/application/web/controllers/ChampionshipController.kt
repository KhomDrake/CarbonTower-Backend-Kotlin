package com.carbontower.application.web.controllers

import com.carbontower.application.web.*
import com.carbontower.domain.entities.database.T_SLACK
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.http.InviteCreateData
import com.carbontower.domain.entities.http.Slack
import com.carbontower.domain.entities.response.*
import com.carbontower.domain.services.championship.ChampionshipService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class ChampionshipController(private val championshipService: ChampionshipService,
                             private val cookie: Cookie) {
    fun routes() {
        path("/campeonato") {
            post("signup",  toJson { signupChampionship(it) })
            get("get",  toJson { getChampionships(it) })
            get("detail/:id",  toJson { getChampionship(it) })
            get(":id/players",  toJson { playersChampionship(it) })
            post("invite/:id/create",  toJson { createInvite(it) })
            get("games/", toJson { getGames(it) })
            get("invites/:id", toJson {  getAllInvites(it) })
            post("administrator/:id-user-administrator/:idchampionship", toJson { insertAdministrator(it) })
            post("/slack", toJson { insertConfigurationSlack(it) })
        }
    }

    private fun insertConfigurationSlack(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val body = ctx.body<Slack>()
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        transaction {
            val idCompany = championshipService.getIdUserRoleCompany(idUser)
            T_SLACK.insert {
                it[T_SLACK.idUserRole_fk] = idCompany
                it[T_SLACK.urlWorkspace] = body.urlWorkspace
                it[T_SLACK.tempCPU] = body.maxTempCPU
                it[T_SLACK.tempGPU] = body.maxTempGPU
                it[T_SLACK.useCPU] = body.maxUseCPU
                it[T_SLACK.useGPU] = body.maxUseGPU
                it[T_SLACK.useDisc] = body.maxUseDisc
                it[T_SLACK.useRam] = body.maxUseRam
            }
        }

        return true
    }

    private fun insertAdministrator(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idChampionship = ctx.pathParam("idchampionship").toInt()
        val idAdministrator = ctx.pathParam("id-user-administrator")
        val idUser: String = cookie.getIdCookie(c.toString())
        championshipService.insertAdministrator(idUser, idChampionship, idAdministrator)
        ctx.insertLogSuccess("Empresa $idUser cadastrou um administrador $idAdministrator no campeonato $idChampionship")
        return true
    }

    private fun getAllInvites(ctx: Context) : List<InviteTotalData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        val listInvites = championshipService.getAllInvitesChampionship(idChampionship, idUser)
        ctx.insertLogSuccess("Empresa $idUser solicitou todos os convites do campeonato $idChampionship com sucesso")
        return listInvites
    }

    private fun getGames(ctx: Context) : List<GameData> {
        val games = championshipService.getGames()
        ctx.insertLogSuccess("Games Pegados com sucesso")
        return games
    }

    private fun createInvite(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        val inviteCreateData = ctx.body<InviteCreateData>()
        championshipService.createInvite(idUser, idChampionship, inviteCreateData)
        ctx.insertLogSuccess("Convite criado com sucesso $inviteCreateData" +
                ". Campeonato: #$idChampionship, Empresa: $idUser")
        return true
    }

    private fun playersChampionship(ctx: Context) : List<PlayerChampionshipData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        val list = championshipService.getPlayersChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Jogadores que participam do campeonato pegados com sucesso" +
                ". Campeonato: #$idChampionship, Empresa: $idUser")
        return list
    }

    private fun signupChampionship(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val signupChampionshipData = ctx.body<SingupChampionshipData>()
        championshipService.signupChampionship(idUser, signupChampionshipData)
        ctx.insertLogSuccess("Cadastro de campeonato feito com sucesso $signupChampionshipData pelo usuário $idUser")
        return true
    }

    private fun getChampionship(ctx: Context) : ChampionshipData {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        val championshipData = championshipService.getChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Dados do campeonato." +
                " Campeonato: #$idChampionship, Empresa: $idUser")
        return championshipData
    }

    private fun getChampionships(ctx: Context) : List<ChampionshipData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val championships = championshipService.getChampionships(idUser)
        ctx.insertLogSuccess("Campeonatos pegos com sucesso." +
                " Empresa: $idUser")
        return championships
    }
}