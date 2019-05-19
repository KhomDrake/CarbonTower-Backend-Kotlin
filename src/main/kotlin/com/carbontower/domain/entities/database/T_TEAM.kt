package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_TEAM : Table() {
    val idTeam = integer("idTeam").primaryKey().autoIncrement()
}