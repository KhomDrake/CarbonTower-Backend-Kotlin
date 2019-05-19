package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_USER_MACHINE : Table() {
    val idUser_fk = integer("idUser_fk").primaryKey(0) references T_USER_ROLE.idUserRole
    val idMachine_fk = integer("idMachine_fk").primaryKey(1) references T_MACHINE.idMachine
}