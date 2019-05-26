package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class NotACompany(private val idUser: String) : Unauthorized() {
    override var message: String = "Não possui permissão"
    override var messageLog: String = "Não foi permitido prosseguir com essa ação, pois usuário ${idUser} " +
            "não tem permissão para fazer tal ação, já que" +
            "não é uma empresa."
}