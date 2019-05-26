package com.carbontower.domain.services.login

import com.carbontower.domain.entities.http.LoginData
import com.carbontower.domain.services.DataValidation
import com.carbontower.resources.Encrypt
import com.carbontower.resources.database.exception.InvalidPassword
import com.carbontower.resources.database.exception.PersonDataInvalid
import com.carbontower.resources.database.exception.UserNotExistLogin

class LoginService(private val loginRepository: ILoginRepository, private val dataValidation: DataValidation,
                   private val encrypt: Encrypt
) {
    fun validLogin(loginData: LoginData) {
        if(dataValidation.validPersonData(loginData.persondata).not()) throw PersonDataInvalid(loginData.persondata)

        if(loginRepository.userExist(loginData.persondata).not()) throw UserNotExistLogin(loginData.persondata)

        if(loginRepository.validPassword(loginData, encrypt.hash512(loginData.password)).not())
            throw InvalidPassword(loginData.persondata, loginData.password)
    }
}