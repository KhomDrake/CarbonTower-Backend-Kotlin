package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_MATCH : Table() {
    val idMatch = integer("idMatch").primaryKey().autoIncrement()
    val idChampionship = integer("idChampionship") references T_CHAMPIONSHIP.idChampionship
    val winner = integer("winner") references T_TEAM.idTeam
    val date = varchar("date", length = 30)
    val time = varchar("time", length = 30)
}