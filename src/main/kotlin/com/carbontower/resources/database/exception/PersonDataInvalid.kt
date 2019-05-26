package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class PersonDataInvalid(private val idUser: String) : NotAcceptable() {
    override var message: String = "Dado(s) Inválidos"
    override var messageLog: String = "CPF or CNPJ inválido: $idUser"
}