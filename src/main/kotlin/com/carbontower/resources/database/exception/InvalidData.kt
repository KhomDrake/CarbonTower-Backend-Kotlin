package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class InvalidData : Unauthorized() {
    override var message: String = "Dado(s) inválido"
}