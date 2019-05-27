package com.carbontower.domain.services.machine

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.DateMetricMachineData
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData
import com.carbontower.domain.entities.response.MachineMetricData
import com.carbontower.resources.database.exception.MachineNotExist
import com.carbontower.resources.database.exception.NotACompany

class MachineService(private val machineRepository: IMachineRepository) {
    fun insertMachine(idUser: String, insertMachineData: InsertMachineData) {
        val idUserRole = getIdUserRole(idUser)
        if(idUserRole == 0) throw NotACompany(idUser)
        val idMachine = machineRepository.insertMachine(insertMachineData)
        machineRepository.insertMachineUser(idUserRole, idMachine)
    }

    private fun getIdUserRole(idUser: String) : Int {
        return machineRepository.getIdUserRole(idUser, Role.Empresa)
    }

    fun getAllMachines(): List<MachineData> {
        return machineRepository.getAllMachines()
    }

    fun getMachine(idMachine: String): MachineData {
        return machineRepository.getMachines(idMachine)
    }

    fun insertMachineMetric(idMachine: String, insertMetricMachineData: InsertMetricMachineData) {
        if(machineRepository.machineExist(idMachine).not()) throw MachineNotExist(idMachine)
        machineRepository.insertMachineMetric(idMachine, insertMetricMachineData)
    }

    fun getMachineMetricByDate(idMachine: String, dateMetricMachineData: DateMetricMachineData): List<MachineMetricData> {
        if(machineRepository.machineExist(idMachine).not()) throw MachineNotExist(idMachine)
        return machineRepository.getMachineMetricByDate(idMachine, dateMetricMachineData)
    }
}