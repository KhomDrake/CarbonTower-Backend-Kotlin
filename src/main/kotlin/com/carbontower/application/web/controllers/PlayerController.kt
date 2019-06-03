package com.carbontower.application.web.controllers

import com.carbontower.application.web.*
import com.carbontower.domain.entities.http.MatchData
import com.carbontower.domain.entities.http.TimeData
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import com.carbontower.domain.entities.response.Match
import com.carbontower.domain.entities.response.Time
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
            post("/time", toJson { insertTime(it) })
            post("/time/:id-time/:id-user", toJson { insertPlayerInTime(it) })
            post("/time/users/:id-time", toJson { insertPlayersInTime(it) })
            post("/match", toJson { insertMatch(it) })
            post("/match/:id-match/:id-time", toJson { insertTimeInMatch(it) })
            post("/match/times/:id-match", toJson { insertTimesInMatch(it) })
            get("/match/detail/:idmatch", toJson { matchById(it) })
            get("/times/:idchampionship", toJson { timesChampionships(it) })
            get("/match/:idchampionship", toJson { matchsChampionship(it) })
            get("/match/player/participate/", toJson { allMatchsParticipate(it) })
            get("/time/player/participate/", toJson { allTimesParticipate(it) })
            get("/match/player/participate/:idchampionship", toJson { matchsParticipateChampionship(it) })
            get("/time/player/participate/:idchampionship", toJson { timesParticipateChampionship(it) })
        }
    }

    private fun matchById(ctx: Context) : Match {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idMatch = ctx.pathParam("idmatch").toInt()
        val match = playerService.getMatchById(idMatch)
        return match
    }

    private fun matchsChampionship(ctx: Context) : List<Match> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idChampionship = ctx.pathParam("idchampionship").toInt()
        val matchs = playerService.getMatchsChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Empresa $idUser solicitou todos as partidas do campeonato $idChampionship")
        return matchs
    }

    private fun allMatchsParticipate(ctx: Context) : List<Match> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val matchs = playerService.getAllMatchsPlayer(idUser)
        ctx.insertLogSuccess("Usuário $idUser solicitou todas as partidas que participou")
        return matchs
    }

    private fun allTimesParticipate(ctx: Context) : List<Time> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val times = playerService.getAllTimesPlayer(idUser)
        ctx.insertLogSuccess("Usuário $idUser solicitou todos os times que participou")
        return times
    }

    private fun matchsParticipateChampionship(ctx: Context) : List<Match> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idChampionship = ctx.pathParam("idchampionship").toInt()
        val matchsChampionship = playerService.getAllMatchsPlayerChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Usuário $idUser solicitou todos as partidas que participou do campeonato $idChampionship")
        return matchsChampionship
    }

    private fun timesParticipateChampionship(ctx: Context) : List<Time> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idChampionship = ctx.pathParam("idchampionship").toInt()
        val timesChampionship = playerService.getAllTimesPlayerChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Usuário $idUser solicitou todos os times que participou do campeonato $idChampionship")
        return timesChampionship
    }

    private fun insertTime(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val timeData = ctx.body<TimeData>()
        playerService.notACompany(idUser)
        playerService.insertTime(timeData)
        ctx.insertLogSuccess("Empresa $idUser cadastrou o time $timeData com sucesso")
        return true
    }

    private fun insertPlayerInTime(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idPlayer = ctx.pathParam("id-user")
        val idTime = ctx.pathParam("id-time").toInt()
        playerService.notACompany(idUser)
        playerService.insertPlayerInTime(idPlayer, idTime)
        ctx.insertLogSuccess("Empresa $idUser cadastrou o player $idPlayer no time $idTime")
        return true
    }

    private fun insertPlayersInTime(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val players = ctx.body<List<String>>()
        val idTime = ctx.pathParam("id-time").toInt()
        playerService.notACompany(idUser)
        for (idPlayer in players) {
            playerService.insertPlayerInTime(idPlayer, idTime)
        }
        ctx.insertLogSuccess("Empresa $idUser cadastrou os players $players no time $idTime")
        return true
    }

    private fun insertMatch(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val matchData = ctx.body<MatchData>()
        playerService.notACompany(idUser)
        playerService.insertMatch(matchData)
        ctx.insertLogSuccess("Empresa $idUser cadastrou a partida $matchData com sucesso")
        return true
    }

    private fun insertTimeInMatch(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idMatch = ctx.pathParam("id-match").toInt()
        val idTime = ctx.pathParam("id-time").toInt()
        playerService.notACompany(idUser)
        playerService.insertTimeInMatch(idMatch, idTime)
        ctx.insertLogSuccess("Empresa $idUser cadastrou o time $idTime na partida $idMatch com sucesso")
        return true
    }

    private fun insertTimesInMatch(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idMatch = ctx.pathParam("id-match").toInt()
        val times = ctx.body<List<Int>>()
        playerService.notACompany(idUser)
        for(idTime in times) {
            playerService.insertTimeInMatch(idMatch, idTime)
        }
        ctx.insertLogSuccess("Empresa $idUser cadastrou os times $times na partida $idMatch com sucesso")
        return true
    }

    private fun timesChampionships(ctx: Context) : List<Time> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idChampionship = ctx.pathParam("idchampionship").toInt()
        val time = playerService.timesInChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Empresa $idUser solicita todos os do campeonato $idChampionship")
        return time
    }

    private fun administerThisChampionship(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        val administer = playerService.administerThisChampionship(idUser, idChampionship)
        ctx.insertLogSuccess("Jogador $administer administra campeonato $idChampionship: $administer")
        return administer
    }

    private fun getAdministratorChampionship(ctx: Context) : List<ChampionshipData> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val championships = playerService.administratorChampionship(idUser)
        ctx.insertLogSuccess("Jogador $idUser solicita todos os campeonato que administra com sucesso")
        return championships
    }

    private fun championshipsParticipate(ctx: Context) : List<ChampionshipData> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val championships = playerService.championshipsParticipate(idUser)
        ctx.insertLogSuccess("Jogador $idUser solicita todos os campeonato que participa com sucesso")
        return championships
    }

    private fun acceptInvite(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        playerService.acceptInvite(idUser, idChampionship)
        ctx.insertLogSuccess("Jogador $idUser aceita convite do campeonato $idChampionship com sucesso")
        return true
    }

    private fun refuseInvite(ctx: Context) : Boolean {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val idChampionship  = ctx.pathParam("idchampionship").toInt()
        playerService.refuseInvite(idUser, idChampionship)
        ctx.insertLogSuccess("Jogador $idUser recusa convite do campeonato $idChampionship com sucesso")
        return true
    }

    private fun getAllInvites(ctx: Context) : List<InviteData> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val listOfInvites: MutableList<InviteData> = playerService.getInvites(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites com sucesso $listOfInvites")
        return listOfInvites.toList()
    }

    private fun getAllInvitesNotAnswered(ctx: Context) : List<InviteData> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val listOfInvites: MutableList<InviteData> = playerService.getInvitesNotAnswered(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites ainda não respondidos com sucesso")
        return listOfInvites.toList()
    }

    private fun getAllInvitesRefused(ctx: Context) : List<InviteData> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val listOfInvites: MutableList<InviteData> = playerService.getInvitesRefused(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites recusados com sucesso")
        return listOfInvites.toList()
    }

    private fun getAllInvitesAccepted(ctx: Context) : List<InviteData> {
        val idUser = ctx.validateCookieAndReturnIdUser(cookie)
        val listOfInvites: MutableList<InviteData> = playerService.getInvitesAccepted(idUser)
        ctx.insertLogSuccess("Jogaodr $idUser solicita todos os convites aceitos com sucesso")
        return listOfInvites.toList()
    }

}