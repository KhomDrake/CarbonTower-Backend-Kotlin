package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NetworkAuthenticationRequired

class HasNoCookie : NetworkAuthenticationRequired() {
    override var message: String = "Não tem permissão para acessar"
    override var messageLog: String = "Não tem o cookie e por isso não pode acessar essa rota."
}