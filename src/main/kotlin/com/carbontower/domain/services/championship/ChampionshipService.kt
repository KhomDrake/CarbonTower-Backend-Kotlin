package com.carbontower.domain.services.championship

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.GameData
import com.carbontower.domain.entities.http.InviteCreateData
import com.carbontower.domain.entities.response.PlayerChampionshipData
import com.carbontower.resources.database.exception.InviteAlreadyExist
import com.carbontower.resources.database.exception.NotACompany
import com.carbontower.resources.database.exception.UserNotExist

class ChampionshipService(private val championshipRepository: IChampionshipRepository) {
    fun signupChampionship(idUser: String, signupChampionshipData: SingupChampionshipData) {
        val idUserRole = getIdUserRole(idUser)
        championshipRepository.insertchampionship(idUserRole, signupChampionshipData)
    }

    private fun getIdUserRole(idUser: String) : Int {
        return championshipRepository.getIdUserRole(idUser, Role.Empresa)
    }

    fun getChampionships(idUser: String) : List<ChampionshipData> {
        val idUserRole = getIdUserRole(idUser)
        return championshipRepository.getChampionships(idUserRole)
    }

    fun getChampionship(idUser: String, idChampionship: Int): ChampionshipData {
        val idUserRole = getIdUserRole(idUser)
        return championshipRepository.getChampionship(idUser, idUserRole, idChampionship)
    }

    fun getPlayersChampionship(idUser: String, idChampionship: Int): List<PlayerChampionshipData> {
        val idUserRole = getIdUserRole(idUser)
        return championshipRepository.getPlayersChampionship(idUserRole, idChampionship)
    }

    fun createInvite(idUser: String, idChampionship: Int, inviteCreateData: InviteCreateData) {
        val idUserRole = getIdUserRole(idUser)
        if(idUserRole == 0) throw NotACompany()

        val idPlayer = championshipRepository.getIdUserRole(inviteCreateData.cpf, Role.Jogador)

        if(idPlayer == 0) throw UserNotExist()

        if(championshipRepository.alreadyExistInvite(idPlayer, idChampionship)) throw InviteAlreadyExist()

        championshipRepository.createInvite(idPlayer, idChampionship)
    }

    fun getGames(): List<GameData> {
        return championshipRepository.getGames()
    }

}