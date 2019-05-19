package com.carbontower.domain.services.signup

import com.carbontower.domain.entities.http.SignupData

interface ISignupRepository {
    fun userExist(signupData: SignupData): Boolean
    fun createUser(signupData: SignupData)
    fun createUserRole(signupData: SignupData)
}