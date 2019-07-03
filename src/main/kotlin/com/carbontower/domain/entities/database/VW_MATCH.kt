package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table


object VW_MATCH : Table() {
    val idChampionship = integer("idChampionship")
    val idMatch = integer("idMatch")
    val nmTeam1 = varchar("nmTeam1", length = 50)
    val nmTeam2 = varchar("nmTeam2", length = 50)
}