package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_METRIC : Table() {
    val idMetric = integer("idMetric").primaryKey().autoIncrement()
    val idStream_fk = integer("idStream_fk") references T_STREAM.idStream
}