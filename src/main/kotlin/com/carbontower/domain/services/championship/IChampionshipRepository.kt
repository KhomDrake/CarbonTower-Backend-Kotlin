package com.carbontower.domain.services.championship

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.PlayerChampionshipData

interface IChampionshipRepository {
    fun getIdUserRole(idUser: String, empresa: Role): Int
    fun insertchampionship(idUserRole: Int, signupChampionshipData: SingupChampionshipData)
    fun getChampionships(idUserRole: Int): List<ChampionshipData>
    fun getChampionship(idUser: String, idUserRole: Int, idChampionship: Int): ChampionshipData
    fun getPlayersChampionship(idUserRole: Int, idChampionship: Int): List<PlayerChampionshipData>
    fun createInvite(idPlayer: Int, idChampionship: Int)
    fun alreadyExistInvite(idPlayer: Int, idChampionship: Int): Boolean
}