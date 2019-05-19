package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_PLAYER_IN_TEAM : Table() {
    val idPlayer_fk = integer("idPlayer_fk").primaryKey(0) references T_USER_ROLE.idUserRole
    val idTeam_fk = integer("idTeam_fk").primaryKey(1) references T_TEAM.idTeam
    init {
        index(true,
            idPlayer_fk,
            idTeam_fk
        )
    }
}