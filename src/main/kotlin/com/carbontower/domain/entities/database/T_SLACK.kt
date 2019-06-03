package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_SLACK: Table() {
    val idSlack = integer("idSlack").primaryKey().autoIncrement()
    val urlWorkspace = varchar("urlWorkspace", length = 300)
    val useRam = decimal("useRam", precision = 2, scale = 6)
    val tempGPU = decimal("tempGPU", precision = 2, scale = 6)
    val useGPU = decimal("useGPU", precision = 2, scale = 6)
    val useDisc = decimal("useDisc", precision = 2, scale = 6)
    val useCPU = decimal("useCPU", precision = 2, scale = 6)
    val tempCPU = decimal("tempCPU", precision = 2, scale = 6)
    val idUserRole_fk = integer("idUserRole_fk") references T_USER_ROLE.idUserRole
}