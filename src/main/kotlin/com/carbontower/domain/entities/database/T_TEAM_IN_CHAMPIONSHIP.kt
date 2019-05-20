package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_TEAM_IN_CHAMPIONSHIP : Table() {
    val idChampionship_fk = integer("idChampionship_fk").primaryKey(0) references T_CHAMPIONSHIP.idChampionship
    val idTeam_fk = integer("idTeam_fk").primaryKey(1) references T_TEAM.idTeam
    init {
        index(true,
            idChampionship_fk,
            idTeam_fk
        )
    }
}