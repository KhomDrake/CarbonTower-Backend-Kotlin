package com.carbontower.application.web

import com.carbontower.domain.services.EncryptCookie
import java.time.LocalDateTime

class Cookie(private val encryptCookie: EncryptCookie) {
    val cookieName: String = "carbontower"

    private val cookies = mutableMapOf<String, String>()

    fun getIdCookie(cookie: String) : String {
        val id = encryptCookie.decryptCookie(cookies.get(cookie)!!)
        println(id)
        return id
    }

    fun setIdCookie(idClient: String, dateTimeCrypt: String) {
        cookies[dateTimeCrypt] = encryptCookie.encryptCookie(idClient)
    }

    fun contains(cookie: String?) = cookies.contains(cookie)

    fun getDateTimeCrypt() = LocalDateTime.now().toString()
}