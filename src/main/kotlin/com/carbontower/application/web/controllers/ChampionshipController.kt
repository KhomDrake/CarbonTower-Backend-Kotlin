package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.toJson
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteCreateData
import com.carbontower.domain.entities.response.PlayerChampionshipData
import com.carbontower.domain.services.championship.ChampionshipService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

class ChampionshipController(private val championshipService: ChampionshipService,
                             private val cookie: Cookie) {
    fun routes() {
        path("/campeonato") {
            get("player/:iduserrole/graphics/:idchampionship",  toJson { test(it) })
            post("signup",  toJson { signupChampionship(it) })
            get("get",  toJson { getChampionships(it) })
            get("detail/:id",  toJson { getChampionship(it) })
            get(":id/players",  toJson { playersChampionship(it) })
            post("invite/:id/create",  toJson { createInvite(it) })
        }
    }

    private fun createInvite(ctx: Context) : Boolean {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        val inviteCreateData = ctx.body<InviteCreateData>()
        championshipService.createInvite(idUser, idChampionship, inviteCreateData)
        return true
    }

    private fun playersChampionship(ctx: Context) : List<PlayerChampionshipData> {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        return championshipService.getPlayersChampionship(idUser, idChampionship)
    }

    private fun signupChampionship(ctx: Context) : Boolean {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val signupChampionshipData = ctx.body<SingupChampionshipData>()
        championshipService.signupChampionship(idUser, signupChampionshipData)
        return true
    }

    private fun getChampionship(ctx: Context) : ChampionshipData {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship = ctx.pathParam("id").toInt()
        return championshipService.getChampionship(idUser, idChampionship)
    }

    private fun getChampionships(ctx: Context) : List<ChampionshipData> {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        return championshipService.getChampionships(idUser)
    }

    fun test(ctx: Context) {

    }
}