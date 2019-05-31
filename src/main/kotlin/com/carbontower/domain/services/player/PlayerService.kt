package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.MatchData
import com.carbontower.domain.entities.http.TimeData
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import com.carbontower.domain.entities.response.Match
import com.carbontower.domain.entities.response.Time
import com.carbontower.resources.database.exception.*


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

    private fun notACompany(idUserRole: Int, idUser: String) {
        if(idUserRole == 0) throw NotACompany(idUser)
    }

    fun notACompany(idUser: String) {
        val idUserRole = getIdUserRoleCompany(idUser)
        if(idUserRole == 0) throw NotACompany(idUser)
    }

    private fun getIdUserRolePlayer(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Jogador)
    }

    private fun getIdUserRoleAdministrator(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Administrador)
    }

    private fun getIdUserRoleCompany(idUser: String) : Int {
        return playerRepository.getIdUserRole(idUser, Role.Empresa)
    }

    fun acceptInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)

        if(playerRepository.isInviteAlreadyAnswered(idUserRole, idChampionship)) throw InviteAlreadyAnswer(idUser, idChampionship)

        playerRepository.acceptInvite(idUserRole, idChampionship)
    }

    fun refuseInvite(idUser: String, idChampionship: Int) {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)

        if(playerRepository.isInviteAlreadyAnswered(idUserRole, idChampionship)) throw InviteAlreadyAnswer(idUser, idChampionship)

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

    fun getInvitesAccepted(idUser: String): MutableList<InviteData> {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)
        return playerRepository.getInvitesAccepted(idUserRole)
    }

    fun getInvitesRefused(idUser: String): MutableList<InviteData> {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)
        return playerRepository.getInvitesRefused(idUserRole)
    }

    fun getInvitesNotAnswered(idUser: String): MutableList<InviteData> {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)
        return playerRepository.getInvitesNotAnswered(idUserRole)
    }

    fun insertTime(timeData: TimeData) {
        var idTime = 0

        idTime = if(playerRepository.existTime(timeData.nmTime)) playerRepository.getIdTime(timeData.nmTime) else {
            playerRepository.insertTime(timeData.nmTime)
            playerRepository.getIdTime(timeData.nmTime)
        }

        if(playerRepository.timeInChampionship(idTime, timeData.idChampionship))
            throw TimeAlreadyInChampionship(idTime, timeData.idChampionship)

        playerRepository.insertTimeInChampionship(idTime, timeData.idChampionship)
    }

    fun insertPlayerInTime(idPlayer: String, idTime: Int) {
        if(playerRepository.existTime(idTime).not()) throw TimeNotExist(idTime)

        val idUserPlayer = getIdUserRolePlayer(idPlayer)
        notAPlayer(idUserPlayer, idPlayer)

        if(playerRepository.playerInTime(idUserPlayer, idTime)) throw PlayerAlreadyInTime(idTime, idPlayer)

        playerRepository.insertPlayerInTime(idUserPlayer, idTime)
    }

    fun insertMatch(matchData: MatchData) {
        playerRepository.insertMatch(matchData)
    }

    fun insertTimeInMatch(idMatch: Int, idTime: Int) {
        if(playerRepository.existTime(idTime)) throw TimeNotExist(idTime)

        if(playerRepository.existMatch(idMatch)) throw MatchNotExist(idMatch)

        playerRepository.insertTimeInMatch(idMatch, idTime)
    }

    fun getPlayerTimeInChampionship(idUser: String, idChampionship: Int): Time {
        val idUserRole = getIdUserRolePlayer(idUser)
        notAPlayer(idUserRole, idUser)

        if(playerRepository.existChampionship(idChampionship).not()) throw ChampionshipNotExist(idChampionship)

        return playerRepository.getPlayerTimeInChampionship(idUserRole, idChampionship)
    }

    fun getMatchsChampionship(idUser: String, idChampionship: String): List<Match> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}