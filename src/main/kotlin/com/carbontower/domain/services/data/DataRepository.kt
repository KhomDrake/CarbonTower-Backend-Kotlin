package com.carbontower.domain.services.data

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.database.T_ROLE
import com.carbontower.domain.entities.database.T_USER
import com.carbontower.domain.entities.database.T_USER_ROLE
import com.carbontower.domain.entities.response.UserData
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class DataRepository: IDataRepository {
    override fun getUserData(idUser: String): UserData {
        var username = ""
        var roles = mutableListOf<Int>()
        var idUserRole = 0

        transaction {
            val user = (T_USER innerJoin T_USER_ROLE innerJoin T_ROLE).select {
                T_USER.idUser.eq(idUser).and(T_USER_ROLE.idUser_fk.eq(idUser)).and(T_USER_ROLE.idRole_fk.eq(T_ROLE.idRole))
            }

            user.forEach {
                username = it[T_USER.nmUser]
                roles.add(it[T_ROLE.idRole])
                idUserRole = it[T_USER_ROLE.idUserRole]
            }
        }
        return UserData(idUser, username, roles.toList(), idUserRole)
    }

    override fun getIdRole(idUser: String, role: Role): Int {
        var idRole = 0

        transaction {
            val results = T_USER_ROLE.select { T_USER_ROLE.idUser_fk.eq(idUser).and(T_USER_ROLE.idRole_fk.eq(role.ordinal)) }
            results.forEach {
                idRole = it[T_USER_ROLE.idUserRole]
            }
        }
        return idRole
    }
}