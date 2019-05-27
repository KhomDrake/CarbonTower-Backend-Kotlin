package com.carbontower.domain.services.machine

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.database.T_MACHINE
import com.carbontower.domain.entities.database.T_MACHINE_METRIC
import com.carbontower.domain.entities.database.T_USER_MACHINE
import com.carbontower.domain.entities.database.T_USER_ROLE
import com.carbontower.domain.entities.http.DateMetricMachineData
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData
import com.carbontower.domain.entities.response.MachineMetricData
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class MachineRepository: IMachineRepository {
    override fun getMachineMetricByDate(
        idMachine: String,
        dateMetricMachineData: DateMetricMachineData
    ): List<MachineMetricData> {
        val machineMetrics = mutableListOf<MachineMetricData>()

        transaction {
            val machineMetricDb = T_MACHINE_METRIC.select { T_MACHINE_METRIC.idMachine_fk.eq(idMachine)
                .and(T_MACHINE_METRIC.metricDate.eq(dateMetricMachineData.date))}

            machineMetricDb.forEach {
                machineMetrics.add(
                    MachineMetricData(
                        it[T_MACHINE_METRIC.useRam],
                        it[T_MACHINE_METRIC.tempGPU],
                        it[T_MACHINE_METRIC.useGPU],
                        it[T_MACHINE_METRIC.useCPU],
                        it[T_MACHINE_METRIC.useDisc],
                        it[T_MACHINE_METRIC.rpmCooler],
                        it[T_MACHINE_METRIC.tempCPU],
                        it[T_MACHINE_METRIC.usbDevice],
                        it[T_MACHINE_METRIC.metricDate],
                        it[T_MACHINE_METRIC.metricTime],
                        it[T_MACHINE_METRIC.idMachineMetric],
                        it[T_MACHINE_METRIC.idMachine_fk]
                    )
                )
            }
        }

        return machineMetrics
    }

    override fun machineExist(idMachine: String): Boolean {
        var exist = false
        transaction {
            exist = T_MACHINE.select { T_MACHINE.idMachine.eq(idMachine) }.count() != 0
        }
        return exist
    }

    override fun insertMachineMetric(idMachine: String, insertMetricMachineData: InsertMetricMachineData) {
        transaction {
            T_MACHINE_METRIC.insert {
                it[T_MACHINE_METRIC.idMachine_fk] = idMachine
                it[T_MACHINE_METRIC.metricDate] = insertMetricMachineData.metricDate
                it[T_MACHINE_METRIC.metricTime] = insertMetricMachineData.metricTime
                it[T_MACHINE_METRIC.rpmCooler] = insertMetricMachineData.rpmCooler
                it[T_MACHINE_METRIC.tempCPU] = insertMetricMachineData.tempCPU
                it[T_MACHINE_METRIC.tempGPU] = insertMetricMachineData.tempGPU
                it[T_MACHINE_METRIC.usbDevice] = insertMetricMachineData.usbDevice
                it[T_MACHINE_METRIC.useDisc] = insertMetricMachineData.useDisc
                it[T_MACHINE_METRIC.useCPU] = insertMetricMachineData.useCPU
                it[T_MACHINE_METRIC.useGPU] = insertMetricMachineData.useGPU
                it[T_MACHINE_METRIC.useRam] = insertMetricMachineData.useRam
            }
        }
    }

    override fun getMachines(idMachine: String): MachineData {
        var motherBoard = ""
        var os = ""
        var manufacturer = ""
        var model = ""

        transaction {
            val machines = T_MACHINE.select { T_MACHINE.idMachine.eq(idMachine) }

            machines.forEach {
                motherBoard = it[T_MACHINE.motherBoard]
                os = it[T_MACHINE.os]
                manufacturer = it[T_MACHINE.manufacturer]
                model = it[T_MACHINE.model]
            }
        }

        return MachineData(motherBoard, os, manufacturer, model, idMachine)
    }

    override fun getAllMachines(): List<MachineData> {
        val listMachines = mutableListOf<MachineData>()

        transaction {
            val machines = T_MACHINE.selectAll()

            machines.forEach {
                listMachines.add(
                    MachineData(
                        it[T_MACHINE.motherBoard],
                        it[T_MACHINE.os],
                        it[T_MACHINE.manufacturer],
                        it[T_MACHINE.model],
                        it[T_MACHINE.idMachine]
                    )
                )
            }
        }

        return listMachines.toList()
    }

    override fun insertMachineUser(idUserRole: Int, idMachine: String) {
        transaction {
            T_USER_MACHINE.insert {
                it[T_USER_MACHINE.idMachine_fk] = idMachine
                it[T_USER_MACHINE.idUser_fk] = idUserRole
            }
        }
    }

    override fun insertMachine(insertMachineData: InsertMachineData): String {
        var idMachine: String? = ""

        transaction {
            idMachine = T_MACHINE.insert {
                it[T_MACHINE.idMachine] = insertMachineData.idMachine
                it[T_MACHINE.manufacturer] = insertMachineData.manufacturer
                it[T_MACHINE.model] = insertMachineData.model
                it[T_MACHINE.motherBoard] = insertMachineData.motherBoard
                it[T_MACHINE.os] = insertMachineData.os
            } get T_MACHINE.idMachine
        }
        return idMachine!!
    }

    override fun getIdUserRole(idUser: String, empresa: Role): Int {
        var idRole = 0

        transaction {
            val results = T_USER_ROLE.select { T_USER_ROLE.idUser_fk.eq(idUser).and(
                T_USER_ROLE.idRole_fk.eq(empresa.ordinal)) }
            results.forEach {
                idRole = it[T_USER_ROLE.idUserRole]
            }
        }
        return idRole
    }
}