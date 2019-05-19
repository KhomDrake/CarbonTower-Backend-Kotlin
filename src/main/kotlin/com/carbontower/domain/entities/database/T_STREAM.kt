package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_STREAM : Table() {
    val idStream = integer("idStream").primaryKey().autoIncrement()
    val typeStream = varchar("typeStream", length = 45)
    val idUserRole = integer("idUserRole") references T_USER_ROLE.idUserRole
}