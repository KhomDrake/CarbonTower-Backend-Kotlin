package com.carbontower.domain.services.machine

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.DateMetricMachineData
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData
import com.carbontower.domain.entities.response.MachineMetricData
import com.carbontower.resources.database.exception.MachineNotExist
import com.carbontower.resources.database.exception.NotAPlayer

class MachineService(private val machineRepository: IMachineRepository) {
    fun createMachine(idUser: String, insertMachineData: InsertMachineData) {
        val idUserRole = machineRepository.getIdUserRole(idUser, Role.Jogador)
        if(idUserRole == 0) throw NotAPlayer(idUser)
        val idMachine = machineRepository.checkMachine(insertMachineData)
        machineRepository.checkMachineUser(idUserRole, idMachine)
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

    fun getLastMachineMetric(idMachine: String): MachineMetricData {
        if(machineRepository.machineExist(idMachine).not()) throw MachineNotExist(idMachine)
        return machineRepository.getLastMachineMetric(idMachine)
    }

    fun getAllMachineMetric(idMachine: String): List<MachineMetricData> {
        if(machineRepository.machineExist(idMachine).not()) throw MachineNotExist(idMachine)
        return machineRepository.getAllMachineMetric(idMachine)
    }
}