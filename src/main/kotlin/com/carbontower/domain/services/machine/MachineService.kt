package com.carbontower.domain.services.machine

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.DateMetricMachineData
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData
import com.carbontower.domain.entities.response.MachineMetricData
import com.carbontower.resources.database.exception.InvalidData
import com.carbontower.resources.database.exception.NotACompany

class MachineService(private val machineRepository: IMachineRepository) {
    fun insertMachine(idUser: String, insertMachineData: InsertMachineData) {
        val idUserRole = getIdUserRole(idUser)
        if(idUserRole == 0) throw NotACompany()

        val idMachine = machineRepository.insertMachine(insertMachineData)

        if(idMachine == 0) throw InvalidData()

        machineRepository.insertMachineUser(idUserRole, idMachine)
    }

    private fun getIdUserRole(idUser: String) : Int {
        return machineRepository.getIdUserRole(idUser, Role.Empresa)
    }

    fun getAllMachines(): List<MachineData> {
        return machineRepository.getAllMachines()
    }

    fun getMachine(id: Int): MachineData {
        return machineRepository.getMachines(id)
    }

    fun insertMachineMetric(id: Int, insertMetricMachineData: InsertMetricMachineData) {
        if(machineRepository.machineExist(id).not()) throw InvalidData()
        machineRepository.insertMachineMetric(id, insertMetricMachineData)
    }

    fun getMachineMetricByDate(idMachine: Int, dateMetricMachineData: DateMetricMachineData): List<MachineMetricData> {
        if(machineRepository.machineExist(idMachine).not()) throw InvalidData()

        return machineRepository.getMachineMetricByDate(idMachine, dateMetricMachineData)
    }
}