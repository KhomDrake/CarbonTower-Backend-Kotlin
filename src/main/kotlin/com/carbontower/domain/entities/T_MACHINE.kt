package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_MACHINE : Table() {
    val idMachine = integer("idMachine").primaryKey().autoIncrement()
    val motherBoard = varchar("motherBoard", length = 45)
    val os = varchar("OS", length = 45)
    val manufacturer = varchar("manufacturer", length = 45)
    val model = varchar("model", length = 45)
}