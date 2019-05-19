package com.carbontower.domain.services.signup

import com.carbontower.domain.entities.database.T_USER
import com.carbontower.domain.entities.database.T_USER_ROLE
import com.carbontower.domain.entities.http.SignupData
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.math.sin

class SignupRepository : ISignupRepository {
    override fun userExist(signupData: SignupData): Boolean {
        var result = false
        transaction {
            result = T_USER.select { T_USER.idUser.eq(signupData.persondata) }.count() != 0
        }
        return result
    }

    override fun createUser(signupData: SignupData) {
        transaction {
            T_USER.insert {
                it[T_USER.idUser] = signupData.persondata
                it[T_USER.nmUser] = signupData.username
                it[T_USER.userPassword] = signupData.password
            }
        }
    }

    override fun createUserRole(signupData: SignupData) {
        transaction {
            var userRole = 1
            if(signupData.persondata.length == 11) userRole = 3
            T_USER_ROLE.insert {
                it[T_USER_ROLE.idRole_fk] = userRole
                it[T_USER_ROLE.idUser_fk] = signupData.persondata
            }
        }
    }
}