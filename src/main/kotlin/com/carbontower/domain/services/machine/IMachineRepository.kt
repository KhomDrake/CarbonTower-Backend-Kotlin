package com.carbontower.domain.services.machine

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.DateMetricMachineData
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData
import com.carbontower.domain.entities.response.MachineMetricData

interface IMachineRepository {
    fun getIdUserRole(idUser: String, empresa: Role): Int
    fun checkMachine(insertMachineData: InsertMachineData): String
    fun insertMachine(insertMachineData: InsertMachineData) : String
    fun checkMachineUser(idUserRole: Int, idMachine: String)
    fun insertMachineUser(idUserRole: Int, idMachine: String)
    fun getAllMachines(): List<MachineData>
    fun getMachines(idMachine: String): MachineData
    fun insertMachineMetric(idMachine: String, insertMetricMachineData: InsertMetricMachineData)
    fun machineExist(idMachine: String): Boolean
    fun getMachineMetricByDate(idMachine: String, dateMetricMachineData: DateMetricMachineData): List<MachineMetricData>
    fun getLastMachineMetric(idMachine: String): MachineMetricData
    fun getAllMachineMetric(idMachine: String): List<MachineMetricData>
    fun getLastMetric(): MachineMetricData
}