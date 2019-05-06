package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_GAME : Table() {
    val idGame = integer("idGame").primaryKey().autoIncrement()
    val nmGame = varchar("nmGame", length = 50)
}