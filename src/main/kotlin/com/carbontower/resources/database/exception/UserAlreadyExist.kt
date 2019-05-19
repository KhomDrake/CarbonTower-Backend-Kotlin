package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class UserAlreadyExist : NotAcceptable() {
    override var message: String = "Usuário Já Existe"
}