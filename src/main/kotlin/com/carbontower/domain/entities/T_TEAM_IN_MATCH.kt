package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_TEAM_IN_MATCH : Table() {
    val idTeam_fk = integer("idTeam_fk").primaryKey(0) references T_TEAM.idTeam
    val idMatch_fk = integer("idMatch_fk").primaryKey(1) references T_MATCH.idMatch
    init {
        index(true, idTeam_fk, idMatch_fk)
    }
}