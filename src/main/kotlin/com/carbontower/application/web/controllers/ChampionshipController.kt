package com.carbontower.application.web.controllers

import com.carbontower.application.web.toJson
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

class ChampionshipController(private val championshipController: ChampionshipController) {
    fun routes() {
        path("/campeonato") {
            get("player/:idUserRole/graphics/:idChampionship",  toJson { test(it) })
            post("signup",  toJson { signupChampionship(it) })
            get("get",  toJson { test(it) })
            get("detail/:id",  toJson { test(it) })
            get(":id/players",  toJson { test(it) })
            post("invite/:id/create",  toJson { test(it) })
            get("invite/:id/accept",  toJson { test(it) })
            get("invite/:id/refuse",  toJson { test(it) })
            get("invite/get",  toJson { test(it) })
        }
    }

    private fun signupChampionship(ctx: Context) {

    }

    fun test(ctx: Context) {

    }
}