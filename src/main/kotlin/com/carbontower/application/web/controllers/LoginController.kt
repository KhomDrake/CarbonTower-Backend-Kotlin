package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.toJson
import com.carbontower.domain.entities.http.LoginData
import com.carbontower.domain.services.login.LoginService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

class LoginController(private val loginService: LoginService, private val cookie: Cookie) {
    fun routes() {
        path("/login") {
            post("/", toJson { validateLogin(it) })
            get("logout", toJson { logout(it) })
            post("/java", toJson { validateLoginWithoutCookie(it) })
            post("/cookie", toJson { validateLoginReturnCookie(it) })
        }
    }

    private fun validateLogin(ctx: Context) : Boolean{
        val loginData = ctx.body<LoginData>()
        loginService.validLogin(loginData)
        val dateTimeCrypt = cookie.getDateTimeCrypt()
        ctx.cookie(cookie.cookieName, dateTimeCrypt)
        cookie.setIdCookie(loginData.persondata, dateTimeCrypt)
        return true
    }

    private fun validateLoginWithoutCookie(ctx: Context) : Boolean {
        val loginData = ctx.body<LoginData>()
        loginService.validLogin(loginData)
        return true
    }

    private fun validateLoginReturnCookie(ctx: Context) : String {
        val loginData = ctx.body<LoginData>()
        loginService.validLogin(loginData)
        val dateTimeCrypt = cookie.getDateTimeCrypt()
        cookie.setIdCookie(loginData.persondata, dateTimeCrypt)
        return dateTimeCrypt
    }

    private fun logout(ctx: Context) : Boolean {
        ctx.removeCookie(cookie.cookieName)
        return true
    }
}