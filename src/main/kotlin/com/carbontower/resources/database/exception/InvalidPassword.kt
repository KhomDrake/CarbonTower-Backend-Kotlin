package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class InvalidPassword(private val idUser: String, private val password: String) : NotAcceptable() {
    override var message: String = "Dado(s) Inválidos"
    override var messageLog: String = "Usuário $idUser, com a senha $password inválida"
}