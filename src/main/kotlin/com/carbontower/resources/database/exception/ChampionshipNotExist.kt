package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class ChampionshipNotExist : NotAcceptable() {
    override var message: String = "Dado(s) Inválidos"
}