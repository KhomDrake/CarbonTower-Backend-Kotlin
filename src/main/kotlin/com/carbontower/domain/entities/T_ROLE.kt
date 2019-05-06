package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_ROLE : Table() {
    val idRole = integer("idRole").primaryKey().autoIncrement()
    val nmRole = varchar("nmRole", length = 45)
}