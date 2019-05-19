package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class InviteAlreadyExist : Unauthorized() {
    override var message: String = "Convite já existe"
}