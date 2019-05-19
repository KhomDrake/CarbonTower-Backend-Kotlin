package com.carbontower.domain.services.login

import com.carbontower.domain.entities.http.LoginData
import com.carbontower.domain.services.DataValidation
import com.carbontower.resources.Encrypt
import com.carbontower.resources.database.exception.InvalidData

class LoginService(private val loginRepository: ILoginRepository, private val dataValidation: DataValidation,
                   private val encrypt: Encrypt
) {
    fun validLogin(loginData: LoginData) {
        if(dataValidation.validPersonData(loginData.persondata).not()) throw InvalidData()

        if(loginRepository.userExist(loginData.persondata).not()) throw InvalidData()

        if(loginRepository.validPassword(loginData, encrypt.hash512(loginData.password)).not()) throw InvalidData()
    }
}