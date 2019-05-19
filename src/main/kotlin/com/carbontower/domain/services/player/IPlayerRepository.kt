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
}