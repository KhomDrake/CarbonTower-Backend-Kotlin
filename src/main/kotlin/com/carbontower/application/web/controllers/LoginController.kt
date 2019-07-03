package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.insertLogSuccess
import com.carbontower.application.web.toJson
import com.carbontower.application.web.validateCookie
import com.carbontower.domain.entities.http.LoginData
import com.carbontower.domain.services.login.LoginService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.*

class LoginController(private val loginService: LoginService, private val cookie: Cookie) {
    fun routes() {
        path("/login") {
            post("/", toJson { validateLogin(it) })
            post("/java", toJson { validateLoginWithoutCookie(it) })
            post("/cookie", toJson { validateLoginReturnCookie(it) })
            get("logout", toJson { logout(it) })
        }
    }

    private fun validateLogin(ctx: Context) : Boolean{
        val loginData = ctx.body<LoginData>()
        loginService.validLogin(loginData)
        val dateTimeCrypt = cookie.getDateTimeCrypt()
        ctx.cookie(cookie.cookieName, dateTimeCrypt)
        cookie.setIdCookie(loginData.persondata, dateTimeCrypt)
        ctx.insertLogSuccess("Login do usuário ${loginData.persondata} efetuado com sucesso")
        return true
    }

    private fun validateLoginWithoutCookie(ctx: Context) : Boolean {
        val loginData = ctx.body<LoginData>()
        loginService.validLogin(loginData)
        ctx.insertLogSuccess("Login do usuário ${loginData.persondata} efetuado com sucesso")
        return true
    }

    private fun validateLoginReturnCookie(ctx: Context) : String {
        val loginData = ctx.body<LoginData>()
        loginService.validLogin(loginData)
        val dateTimeCrypt = cookie.getDateTimeCrypt()
        cookie.setIdCookie(loginData.persondata, dateTimeCrypt)
        ctx.insertLogSuccess("Login do usuário ${loginData.persondata} efetuado com sucesso")
        return dateTimeCrypt
    }

    private fun logout(ctx: Context) : Boolean {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        cookie.removeCookie(cookie.cookieName)
        ctx.removeCookie(cookie.cookieName)
        ctx.insertLogSuccess("Logout do usuário $idUser efetuado com sucesso")
        return true
    }
}