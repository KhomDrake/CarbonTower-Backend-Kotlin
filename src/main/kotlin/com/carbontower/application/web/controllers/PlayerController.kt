package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.toJson
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import com.carbontower.domain.services.player.PlayerService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get

class PlayerController(private val playerService: PlayerService, private val cookie: Cookie) {
    fun routes() {
        path("/player") {
            get("/invites/:idchampionship/accept", ::acceptInvite)
            get("/invites/:idchampionship/refuse", ::refuseInvite)
            get("/invites/get", toJson { getInvites(it) })
            get("/championship", toJson { championshipsParticipate(it) })
            get("/championship/administrator", toJson { getAdministratorChampionship(it) })
            get("/championship/administrator/:idchampionship", toJson { administerThisChampionship(it) })
        }
    }

    private fun administerThisChampionship(ctx: Context) : Boolean {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        return playerService.administerThisChampionship(idUser, idChampionship)
    }

    private fun getAdministratorChampionship(ctx: Context) : List<ChampionshipData> {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        return playerService.administratorChampionship(idUser)
    }

    private fun championshipsParticipate(ctx: Context) : List<ChampionshipData> {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        return playerService.championshipsParticipate(idUser)
    }

    private fun acceptInvite(ctx: Context) {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        playerService.acceptInvite(idUser, idChampionship)
    }

    private fun refuseInvite(ctx: Context) {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        playerService.refuseInvite(idUser, idChampionship)
    }

    private fun getInvites(ctx: Context) : List<InviteData> {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val listOfInvites: MutableList<InviteData> = playerService.getInvites(idUser)
        return listOfInvites.toList()
    }
}