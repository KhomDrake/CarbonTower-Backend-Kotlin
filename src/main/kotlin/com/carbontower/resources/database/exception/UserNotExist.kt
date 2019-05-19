package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class UserNotExist : NotAcceptable() {
    override var message: String = "Dado(s) Inválidos"
}