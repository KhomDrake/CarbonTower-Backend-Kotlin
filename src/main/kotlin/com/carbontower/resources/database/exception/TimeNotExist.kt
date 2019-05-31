package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class TimeNotExist(private val idTime: Int) : NotAcceptable() {
    override var message: String = "Dado do time inválido"
    override var messageLog: String = "Time $idTime não existe."
}