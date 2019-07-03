package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object VW_INVITE : Table() {
    val idChampionship = integer("idChampionship")
    val nmUser = varchar("nmUser", length = 50)
    val nmChampionship = varchar("nmChampionship", length = 50)
    val accepted = integer("accepted")
}