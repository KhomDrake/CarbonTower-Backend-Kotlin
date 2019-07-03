package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class NotTheOwnerChampionship(private val idChampionship: Int, private val idUserRole: Int) : Unauthorized() {
    override var message: String = "Não tem permissão para visualizar"
    override var messageLog: String = "Campeonato de id $idChampionship não é da empresa $idUserRole."
}