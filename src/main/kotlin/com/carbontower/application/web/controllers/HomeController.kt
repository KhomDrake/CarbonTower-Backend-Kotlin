package com.carbontower.application.web.controllers

import com.carbontower.application.web.toJson
import com.carbontower.domain.services.home.HomeService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get

class HomeController(private val homeService: HomeService) {
    fun routes() {
        path("/") {
            get("/", ::home)
        }
    }

    private fun home(ctx: Context) {
        ctx.redirect("index.html")
    }
}