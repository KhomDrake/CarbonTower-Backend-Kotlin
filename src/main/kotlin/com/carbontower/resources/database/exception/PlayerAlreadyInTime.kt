package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class PlayerAlreadyInTime(private val idTime: Int, private val idUser: String) : NotAcceptable() {
    override var message: String = "Jogador já está no time"
    override var messageLog: String = "Jogador $idUser já está no time $idTime"
}