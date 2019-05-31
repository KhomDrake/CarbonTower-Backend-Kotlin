package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.MatchData
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import com.carbontower.domain.entities.response.Time

interface IPlayerRepository {
    fun getInvites(idUserRole: Int): MutableList<InviteData>
    fun getIdUserRole(idUserRole: String, jogador: Role): Int
    fun acceptInvite(idUserRole: Int, idChampionship: Int)
    fun refuseInvite(idUserRole: Int, idChampionship: Int)
    fun championshipsParticipate(idUserRole: Int): List<ChampionshipData>
    fun administratorChampionship(idUserRole: Int): List<ChampionshipData>
    fun administerThisChampionship(idUserRole: Int, idChampionship: Int): Boolean
    fun existChampionship(idChampionship: Int): Boolean
    fun getInvitesAccepted(idUserRole: Int): MutableList<InviteData>
    fun getInvitesRefused(idUserRole: Int): MutableList<InviteData>
    fun getInvitesNotAnswered(idUserRole: Int): MutableList<InviteData>
    fun isInviteAlreadyAnswered(idUserRole: Int, idChampionship: Int): Boolean
    fun existTime(nmTime: String): Boolean
    fun existTime(idTime: Int): Boolean
    fun getIdTime(nmTime: String): Int
    fun insertTime(nmTime: String)
    fun timeInChampionship(idTime: Int, idChampionship: Int): Boolean
    fun insertTimeInChampionship(idTime: Int, idChampionship: Int)
    fun playerInTime(idUserPlayer: Int, idTime: Int): Boolean
    fun insertPlayerInTime(idUserPlayer: Int, idTime: Int)
    fun insertMatch(matchData: MatchData)
    fun existMatch(idMatch: Int): Boolean
    fun insertTimeInMatch(idMatch: Int, idTime: Int)
    fun getPlayerTimeInChampionship(idUserRole: Int, idChampionship: Int): Time
}