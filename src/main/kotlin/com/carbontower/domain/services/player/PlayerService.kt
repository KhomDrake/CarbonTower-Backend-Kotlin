package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import com.carbontower.resources.database.exception.NotAPlayer


class PlayerService(private val playerRepository: IPlayerRepository) {
    fun getInvites(idUser: String): MutableList<InviteData> {
        val idUserRole = getIdUserRole(idUser)
        if(idUserRole == 0) throw NotAPlayer()
        return playerRepository.getInvites(idUserRole)
    }

    private fun getIdUserRole(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Jogador)
    }

    fun acceptInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRole(idUser)
        if(idUserRole == 0) throw NotAPlayer()
        playerRepository.acceptInvite(idUserRole, idChampionship)
    }

    fun refuseInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRole(idUser)
        if(idUserRole == 0) throw NotAPlayer()
        playerRepository.refuseInvite(idUserRole, idChampionship)
    }

    fun championshipsParticipate(idUser: String): List<ChampionshipData> {
        val idUserRole = getIdUserRole(idUser)
        if(idUserRole == 0) throw NotAPlayer()

        return playerRepository.championshipsParticipate(idUserRole)
    }

}