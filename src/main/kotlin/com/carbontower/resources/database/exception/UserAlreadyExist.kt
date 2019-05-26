package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class UserAlreadyExist(private val idUser: String) : NotAcceptable() {
    override var message: String = "Não foi possível cadastrar usuário."
    override var messageLog: String = "Usuário $idUser já existe"
}