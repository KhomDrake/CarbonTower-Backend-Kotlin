package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class HasNoCookie : Unauthorized() {
    override var message: String = "Não tem permissão para acessar"
    override var messageLog: String = "Não tem o cookie e por isso não pode acessar essa rota."
}