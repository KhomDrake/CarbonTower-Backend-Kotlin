package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.toJson
import com.carbontower.application.web.validateCookie
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.GameData
import com.carbontower.domain.entities.http.InviteCreateData
import com.carbontower.domain.entities.response.PlayerChampionshipData
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
            
        }
    }

    private fun getGames(ctx: Context) : List<GameData> {
        ctx.validateCookie(cookie)
        val games = championshipService.getGames()
        return games
    }

    private fun createInvite(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        val inviteCreateData = ctx.body<InviteCreateData>()
        championshipService.createInvite(idUser, idChampionship, inviteCreateData)
        return true
    }

    private fun playersChampionship(ctx: Context) : List<PlayerChampionshipData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        return championshipService.getPlayersChampionship(idUser, idChampionship)
    }

    private fun signupChampionship(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val signupChampionshipData = ctx.body<SingupChampionshipData>()
        championshipService.signupChampionship(idUser, signupChampionshipData)
        return true
    }

    private fun getChampionship(ctx: Context) : ChampionshipData {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        return championshipService.getChampionship(idUser, idChampionship)
    }

    private fun getChampionships(ctx: Context) : List<ChampionshipData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        return championshipService.getChampionships(idUser)
    }
}