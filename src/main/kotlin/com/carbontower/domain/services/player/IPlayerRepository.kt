package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData

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
}