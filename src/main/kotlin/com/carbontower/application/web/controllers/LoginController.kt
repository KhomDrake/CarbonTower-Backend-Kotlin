package com.carbontower.application.web.controllers

import com.carbontower.application.web.toJson
import com.carbontower.domain.services.login.LoginService
import io.javalin.apibuilder.ApiBuilder.*

class LoginController(private val loginService: LoginService) {
    fun routes() {
        path("/login") {
            get("/", toJson {  })
            post("/", toJson {  })
            get("logout", toJson {  })
        }
    }
}