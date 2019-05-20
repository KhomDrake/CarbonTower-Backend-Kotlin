package com.carbontower.domain.entities.database

import org.jetbrains.exposed.sql.Table

object T_MACHINE_METRIC : Table() {
    val idMachineMetric = integer("idMachineMetric").primaryKey().autoIncrement()
    val useRam = decimal("useRam", precision = 2, scale = 6)
    val tempGPU = decimal("tempGPU", precision = 2, scale = 6)
    val useGPU = decimal("useGPU", precision = 2, scale = 6)
    val useDisc = decimal("useDisc", precision = 2, scale = 6)
    val useCPU = decimal("useCPU", precision = 2, scale = 6)
    val rpmCooler = integer("rpmCooler")
    val tempCPU = decimal("tempCPU", precision = 2, scale = 6)
    val usbDevice = varchar("usbDevice", length = 45)
    val metricDate = varchar("metricDate", length = 12)
    val metricTime = varchar("metricTime", length = 10)
    val idMachine_fk = integer("idMachine_fk") references T_MACHINE.idMachine
}