package com.carbontower.application.web.controllers

import com.carbontower.application.web.toJson
import com.carbontower.domain.services.signup.SignupService
import io.javalin.apibuilder.ApiBuilder.*

class SignupController(private val signupService: SignupService) {
    fun routes() {
        path("/signup") {
            get("/", toJson {  })
            post("/", toJson {  })
        }
    }
}