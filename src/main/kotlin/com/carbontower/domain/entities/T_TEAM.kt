package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_TEAM : Table() {
    val idTeam = integer("idTeam").primaryKey().autoIncrement()
}