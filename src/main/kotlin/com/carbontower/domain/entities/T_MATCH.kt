package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_MATCH : Table() {
    val idMatch = integer("idMatch").primaryKey().autoIncrement()
    val idChampionship = integer("idChampionship") references T_CHAMPIONSHIP.idChampionship
}