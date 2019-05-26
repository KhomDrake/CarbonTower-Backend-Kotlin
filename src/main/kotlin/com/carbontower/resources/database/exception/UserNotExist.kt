package com.carbontower.resources.database.exception

import com.carbontower.application.web.Role
import com.carbontower.application.web.exceptions.NotAcceptable

class UserNotExist(private val idUser: String, private val role: Role) : NotAcceptable() {
    override var message: String = "Dado(s) Inválidos"
    override var messageLog: String = "Usuário $idUser não existe, papel: ${role.name}"
}