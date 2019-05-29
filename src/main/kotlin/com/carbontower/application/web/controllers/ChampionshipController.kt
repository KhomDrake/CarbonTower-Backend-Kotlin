package com.carbontower.application.web.controllers

import com.carbontower.application.web.*
import com.carbontower.domain.entities.application.LogApplication
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.http.InviteCreateData
import com.carbontower.domain.entities.response.*
import com.carbontower.domain.services.championship.ChampionshipService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

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
        }
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