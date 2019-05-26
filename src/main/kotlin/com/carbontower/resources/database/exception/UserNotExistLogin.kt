package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class UserNotExistLogin(private val idUser: String) : NotAcceptable() {
    override var message: String = "Dado(s) Inválidos"
    override var messageLog: String = "Esse usuário não existe, $idUser, então não pode fazer login"
}