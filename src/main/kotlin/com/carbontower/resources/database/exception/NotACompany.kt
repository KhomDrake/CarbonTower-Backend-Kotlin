package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class NotACompany : Unauthorized() {
    override var message: String = "Você não tem a permissão para fazer essa ação."
}