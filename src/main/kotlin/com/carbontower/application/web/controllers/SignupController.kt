package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.toJson
import com.carbontower.domain.entities.http.LoginData
import com.carbontower.domain.entities.http.SignupData
import com.carbontower.domain.services.signup.SignupService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

class SignupController(private val signupService: SignupService, private val cookie: Cookie) {
    fun routes() {
        path("/signup") {
            post("/", toJson { signup(it) })
        }
    }

    private fun signup(ctx: Context) : Boolean {
        val signupData = ctx.body<SignupData>()
        signupService.signupUser(signupData)
        val dateTimeCrypt = cookie.getDateTimeCrypt()
        ctx.cookie(cookie.cookieName, dateTimeCrypt)
        cookie.setIdCookie(signupData.persondata, dateTimeCrypt)
        return true
    }
}