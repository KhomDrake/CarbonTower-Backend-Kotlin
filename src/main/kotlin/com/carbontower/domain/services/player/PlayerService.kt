package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import com.carbontower.resources.database.exception.ChampionshipNotExist
import com.carbontower.resources.database.exception.NotAAdministrator
import com.carbontower.resources.database.exception.NotAPlayer


class PlayerService(private val playerRepository: IPlayerRepository) {
    fun getInvites(idUser: String): MutableList<InviteData> {
        val idUserRole = getIdUserRolePlayer(idUser)
        if(idUserRole == 0) throw NotAPlayer()
        return playerRepository.getInvites(idUserRole)
    }

    private fun getIdUserRolePlayer(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Jogador)
    }

    private fun getIdUserRoleAdministrator(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Administrador)
    }

    fun acceptInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRolePlayer(idUser)
        if(idUserRole == 0) throw NotAPlayer()
        playerRepository.acceptInvite(idUserRole, idChampionship)
    }

    fun refuseInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRolePlayer(idUser)
        if(idUserRole == 0) throw NotAPlayer()
        playerRepository.refuseInvite(idUserRole, idChampionship)
    }

    fun championshipsParticipate(idUser: String): List<ChampionshipData> {
        val idUserRole = getIdUserRolePlayer(idUser)
        if(idUserRole == 0) throw NotAPlayer()

        return playerRepository.championshipsParticipate(idUserRole)
    }

    fun administratorChampionship(idUser: String): List<ChampionshipData> {
        val idUserRole = getIdUserRoleAdministrator(idUser)
        if(idUserRole == 0) throw NotAAdministrator()

        return playerRepository.administratorChampionship(idUserRole)
    }

    fun administerThisChampionship(idUser: String, idChampionship: Int): Boolean {
        val idUserRole = getIdUserRoleAdministrator(idUser)
        if(idUserRole == 0) return false

        if(playerRepository.existChampionship(idChampionship)) throw ChampionshipNotExist()

        return playerRepository.administerThisChampionship(idUserRole, idChampionship)
    }

}