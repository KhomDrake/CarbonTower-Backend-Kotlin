package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_LOGS_SERVER : Table() {
    val idLogsServer = integer("idLogsServer").autoIncrement().primaryKey()
    val router = varchar("router", length = 50)
    val statusCode = integer("statusCode")
    val message = varchar("message", length = 2000)
    val dateTime = varchar("dateTime", length = 30)
    val method = varchar("method", length = 20)
    val server = varchar("server", length = 30)
}