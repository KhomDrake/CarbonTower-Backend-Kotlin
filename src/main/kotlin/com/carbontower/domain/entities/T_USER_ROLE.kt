package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_USER_ROLE : Table() {
    val idUserRole = integer("idUserRole").primaryKey().autoIncrement()
    val idUser_fk = varchar("idUser_fk", length = 14) references T_USER.idUser
    val idRole_fk = integer("idRole_fk") references T_ROLE.idRole
}