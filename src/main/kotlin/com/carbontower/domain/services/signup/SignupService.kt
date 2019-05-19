package com.carbontower.domain.services.signup

import com.carbontower.domain.entities.http.SignupData
import com.carbontower.domain.services.DataValidation
import com.carbontower.resources.Encrypt
import com.carbontower.resources.database.exception.PersonDataInvalid
import com.carbontower.resources.database.exception.UserAlreadyExist

class SignupService(private val signupRepository: ISignupRepository, private val dataValidation: DataValidation,
                    private val encrypt: Encrypt) {

    fun signupUser(signupData: SignupData) {
        if(dataValidation.validPersonData(signupData.persondata).not()) throw PersonDataInvalid()

        if(signupRepository.userExist(signupData)) throw UserAlreadyExist()

        val signupDataEncryptPassword = SignupData(
            persondata = signupData.persondata,
            username = signupData.username,
            password = signupData.password //encrypt.hash512(signupData.password)
        )

        println(signupDataEncryptPassword.password.length)

        signupRepository.createUser(signupDataEncryptPassword)
        signupRepository.createUserRole(signupData)
    }
}