package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class PersonDataInvalid : Unauthorized() {
    override var message: String = "Dado inválido"
}