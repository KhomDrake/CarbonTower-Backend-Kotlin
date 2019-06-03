package com.carbontower.domain.services.championship

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.http.InviteCreateData
import com.carbontower.domain.entities.response.*
import com.carbontower.resources.database.exception.*

class ChampionshipService(private val championshipRepository: IChampionshipRepository) {
    fun signupChampionship(idUser: String, signupChampionshipData: SingupChampionshipData) {
        val idUserRole = getIdUserRoleCompany(idUser)
        notACompany(idUserRole, idUser)
        championshipRepository.insertchampionship(idUserRole, signupChampionshipData)
    }

    private fun getIdUserRoleCompany(idUser: String) : Int {
        return championshipRepository.getIdUserRole(idUser, Role.Empresa)
    }

    fun getChampionships(idUser: String) : List<ChampionshipData> {
        val idUserRole = getIdUserRoleCompany(idUser)
        notACompany(idUserRole, idUser)
        return championshipRepository.getChampionships(idUserRole)
    }

    fun getChampionship(idUser: String, idChampionship: Int): ChampionshipData {
        val idUserRole = getIdUserRoleCompany(idUser)
        notACompany(idUserRole, idUser)
        existChampionship(idChampionship)
        return championshipRepository.getChampionship(idUser, idUserRole, idChampionship)
    }

    fun getPlayersChampionship(idUser: String, idChampionship: Int): List<PlayerChampionshipData> {
        val idUserRole = getIdUserRoleCompany(idUser)
        notACompany(idUserRole, idUser)
        existChampionship(idChampionship)
        return championshipRepository.getPlayersChampionship(idUserRole, idChampionship)
    }

    private fun notACompany(idUserRole: Int, idUser: String) {
        if(idUserRole == 0) throw NotACompany(idUser)
    }

    fun createInvite(idUser: String, idChampionship: Int, inviteCreateData: InviteCreateData) {
        val idUserRole = getIdUserRoleCompany(idUser)
        notACompany(idUserRole, idUser)

        val idPlayer = championshipRepository.getIdUserRole(inviteCreateData.cpf, Role.Jogador)

        notACompany(idUserRole, idUser)
        existChampionship(idChampionship)

        if(championshipRepository.alreadyExistInvite(idPlayer, idChampionship))
            throw InviteAlreadyExist(idUser, idChampionship)

        championshipRepository.createInvite(idPlayer, idChampionship)
    }

    fun getGames(): List<GameData> {
        return championshipRepository.getGames()
    }

    private fun notACompanyOrAdministrator(idUser: String) {
        val idUSerRoleCompany = getIdUserRoleCompany(idUser)
        val idUerRoleAdministrator = championshipRepository.getIdUserRole(idUser, Role.Administrador)

        if(idUSerRoleCompany == 0 && idUerRoleAdministrator == 0) throw NotACompany(idUser)
    }

    fun getAllInvitesChampionship(idChampionship: Int, idUser: String): List<InviteTotalData> {
        notACompanyOrAdministrator(idUser)
        existChampionship(idChampionship)
        return championshipRepository.getAllInvitesChampionship(idChampionship)
    }

    private fun existChampionship(idChampionship: Int){
        if(championshipRepository.existChampionship(idChampionship).not()) throw ChampionshipNotExist(idChampionship)
    }

    fun insertAdministrator(idUser: String, idChampionship: Int, idAdministrator: String) {
        val idUserRole = getIdUserRoleCompany(idUser)
        notACompany(idUserRole, idUser)
        existChampionship(idChampionship)

        if(championshipRepository.getIdUserRole(idAdministrator, Role.Jogador) == 0)
            throw UserNotExist(idUser, role = Role.Jogador)

        if(championshipRepository.getIdUserRole(idAdministrator, Role.Administrador) != 0)
            throw AdministratorAlreadyExist(idAdministrator, idChampionship)

        championshipRepository.insertRoleAdministrator(idAdministrator)
        val idUserAdministrator = championshipRepository.getIdUserRole(idAdministrator, Role.Administrador)
        championshipRepository.insertAdministratorInChampionship(idUserAdministrator, idChampionship)
    }

}