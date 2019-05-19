package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_PLAYER_IN_CHAMPIONSHIP : Table() {
    val idPlayer_fk = integer("idPlayer_fk").primaryKey(0) references T_USER_ROLE.idUserRole
    val idChampionship_fk = integer("idChampionship_fk").primaryKey(1) references T_CHAMPIONSHIP.idChampionship
    init {
        index(true,
            idPlayer_fk,
            idChampionship_fk
        )
    }
}