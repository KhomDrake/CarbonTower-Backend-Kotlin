package com.carbontower.application.web.controllers

import com.carbontower.domain.services.player.PlayerService
import io.javalin.apibuilder.ApiBuilder.path

class PlayerController(private val playerService: PlayerService) {
    fun routes() {
        path("/player") {

        }
    }
}