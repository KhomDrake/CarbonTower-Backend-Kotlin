package com.carbontower.domain.services.login

import com.carbontower.domain.entities.http.LoginData

interface ILoginRepository {
    fun userExist(persondata: String): Boolean
    fun validPassword(loginData: LoginData, encryptPassword: String): Boolean
}