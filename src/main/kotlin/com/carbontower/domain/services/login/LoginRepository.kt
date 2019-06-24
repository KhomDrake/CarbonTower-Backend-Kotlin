package com.carbontower.domain.services.login

import com.carbontower.domain.entities.database.T_USER
import com.carbontower.domain.entities.http.LoginData
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class LoginRepository : ILoginRepository {
    override fun userExist(persondata: String): Boolean {
        var result = false

        println(persondata)

        transaction {
            result = T_USER.select { T_USER.idUser.eq(persondata) }.count() != 0
        }

        return result
    }

    override fun validPassword(loginData: LoginData, encryptPassword: String): Boolean {
        var result = false

        transaction {
            result = T_USER.select { T_USER.idUser.eq(loginData.persondata)
                .and(T_USER.userPassword.eq(encryptPassword)) }.count() != 0
        }

        return result
    }
}