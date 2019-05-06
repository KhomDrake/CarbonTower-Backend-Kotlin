package com.carbontower.domain.entities

import org.jetbrains.exposed.sql.Table

object T_CHAMPIONSHIP : Table() {
    val idChampionship = integer("idChampionship").primaryKey().autoIncrement()
    val idGame_fk = integer("idGame_fk") references T_GAME.idGame
    val owner_fk = integer("owner_fk") references T_USER_ROLE.idUserRole
}