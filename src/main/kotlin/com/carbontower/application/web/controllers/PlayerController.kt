package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.insertLogSuccess
import com.carbontower.application.web.toJson
import com.carbontower.application.web.validateCookie
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import com.carbontower.domain.services.player.PlayerService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

class PlayerController(private val playerService: PlayerService, private val cookie: Cookie) {
    fun routes() {
        path("/player") {
            get("/invites/:idchampionship/accept", toJson { acceptInvite(it) })
            get("/invites/:idchampionship/refuse", toJson { refuseInvite(it) })
            get("/all-invites", toJson { getAllInvites(it) })
            get("/all-invites/refused", toJson { getAllInvitesRefused(it) })
            get("/all-invites/accepted", toJson { getAllInvitesAccepted(it) })
            get("/all-invites/not-answered", toJson { getAllInvitesNotAnswered(it) })
            get("/championship", toJson { championshipsParticipate(it) })
            get("/championship/administrator", toJson { getAdministratorChampionship(it) })
            get("/championship/administrator/:idchampionship", toJson { administerThisChampionship(it) })
            // cadastro time
            post("/time", toJson {  })
            // cadastroJogadorNoTime
            post("/time/:id-time/:id-user", toJson {  })
            // cadastro Jogadores No Time
            post("/time/users", toJson {  })
            // cadastro partida
            post("/match", toJson {  })
            // cadastroTimeNaPartida
            post("/match/:id-match/:id-time", toJson {  })
            // cadastro Times Na Partida
            post("/match/times/:id-match", toJson {  })
            // pegarTimeJogadorCampeonato
            get("/times/:id-user/:idchampionship", toJson {  })
            // pegarPartidasCampeonato
            get("/match/:idchampionship", toJson {  })
            // pegarPartidasQueParticipei
            get("/match/player/participate/:id-user", toJson {  })
            // pegarTimesQueParticipei
            get("/time/player/participate/:id-user", toJson {  })
        }
    }

    private fun insertTime(ctx: Context) : Boolean {
        return true
    }

    private fun insertPlayerInTime(ctx: Context) : Boolean {
        return true
    }

    private fun insertPlayersInTime(ctx: Context) : Boolean {
        return true
    }

    private fun insertMatch(ctx: Context) : Boolean {
        return true
    }

    private fun insertTimeInMatch(ctx: Context) : Boolean {
        return true
    }

    private fun insertTimesInMatch(ctx: Context) : Boolean {
        return true
    }

    private fun timesChampionships(ctx: Context) {

    }

    private fun matchsChampionship(ctx: Context) {

    }

    private fun matchsParticipate(ctx: Context) {

    }

    private fun timesParticipate(ctx: Context) {

    }



    //

    private fun administerThisChampionship(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        val administer = playerService.administerThisChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Jogador $administer administra campeonato $idChampionship: $administer")
        return administer
    }

    private fun getAdministratorChampionship(ctx: Context) : List<ChampionshipData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val championships = playerService.administratorChampionship(idUser)
        ctx.insertLogSuccess("Jogador $idUser solicita todos os campeonato que administra com sucesso")
        return championships
    }

    private fun championshipsParticipate(ctx: Context) : List<ChampionshipData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val championships = playerService.championshipsParticipate(idUser)
        ctx.insertLogSuccess("Jogador $idUser solicita todos os campeonato que participa com sucesso")
        return championships
    }

    private fun acceptInvite(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        playerService.acceptInvite(idUser, idChampionship)
        ctx.insertLogSuccess("Jogador $idUser aceita convite do campeonato $idChampionship com sucesso")
        return true
    }

    private fun refuseInvite(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        playerService.refuseInvite(idUser, idChampionship)
        ctx.insertLogSuccess("Jogador $idUser recusa convite do campeonato $idChampionship com sucesso")
        return true
    }

    private fun getAllInvites(ctx: Context) : List<InviteData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val listOfInvites: MutableList<InviteData> = playerService.getInvites(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites com sucesso $listOfInvites")
        return listOfInvites.toList()
    }

    private fun getAllInvitesNotAnswered(ctx: Context) : List<InviteData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val listOfInvites: MutableList<InviteData> = playerService.getInvitesNotAnswered(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites ainda não respondidos com sucesso")
        return listOfInvites.toList()
    }

    private fun getAllInvitesRefused(ctx: Context) : List<InviteData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val listOfInvites: MutableList<InviteData> = playerService.getInvitesRefused(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites recusados com sucesso")
        return listOfInvites.toList()
    }

    private fun getAllInvitesAccepted(ctx: Context) : List<InviteData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val listOfInvites: MutableList<InviteData> = playerService.getInvitesAccepted(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites aceitos com sucesso")
        return listOfInvites.toList()
    }
}