package com.carbontower.domain.services

import com.carbontower.resources.Encrypt

class EncryptCookie(private val encrypt: Encrypt) {
    fun encryptCookie(text: String) = encrypt.encrypt(text)

    fun decryptCookie(cookie: String) = encrypt.decrypt(cookie).trim()
}