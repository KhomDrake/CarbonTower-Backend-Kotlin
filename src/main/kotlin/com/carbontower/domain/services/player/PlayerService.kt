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
        notAPlayer(idUserRole, idUser)
        return playerRepository.getInvites(idUserRole)
    }

    private fun notAPlayer(idUserRole: Int, idUser: String) {
        if(idUserRole == 0) throw NotAPlayer(idUser)
    }

    private fun notAAdministrator(idUserRole: Int, idUser: String) {
        if(idUserRole == 0) throw NotAAdministrator(idUser)
    }

    private fun getIdUserRolePlayer(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Jogador)
    }

    private fun getIdUserRoleAdministrator(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Administrador)
    }

    fun acceptInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)
        playerRepository.acceptInvite(idUserRole, idChampionship)
    }

    fun refuseInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)
        playerRepository.refuseInvite(idUserRole, idChampionship)
    }

    fun championshipsParticipate(idUser: String): List<ChampionshipData> {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)
        return playerRepository.championshipsParticipate(idUserRole)
    }

    fun administratorChampionship(idUser: String): List<ChampionshipData> {
        val idUserRole = getIdUserRoleAdministrator(idUser)
        notAAdministrator(idUserRole, idUser)
        return playerRepository.administratorChampionship(idUserRole)
    }

    fun administerThisChampionship(idUser: String, idChampionship: Int): Boolean {
        val idUserRole = getIdUserRoleAdministrator(idUser)
        if(idUserRole == 0) return false

        if(playerRepository.existChampionship(idChampionship)) throw ChampionshipNotExist(idChampionship)

        return playerRepository.administerThisChampionship(idUserRole, idChampionship)
    }

}