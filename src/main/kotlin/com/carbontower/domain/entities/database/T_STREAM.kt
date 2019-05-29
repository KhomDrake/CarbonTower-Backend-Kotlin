package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_STREAM : Table() {
    val idStream = integer("idStream").primaryKey()
    val language = varchar("language", length = 50)
    val title = varchar("title", length = 50)
    val idUserStream_fk = integer("idUserStream_fk") references T_USER_STREAM.idUserStream
    val viewCount = integer("viewCount")
}