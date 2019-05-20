package com.carbontower.domain.services.machine

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData

interface IMachineRepository {
    fun getIdUserRole(idUser: String, empresa: Role): Int
    fun insertMachine(insertMachineData: InsertMachineData) : Int
    fun insertMachineUser(idUserRole: Int, idMachine: Int)
    fun getAllMachines(): List<MachineData>
    fun getMachines(id: Int): MachineData
    fun insertMachineMetric(id: Int, insertMetricMachineData: InsertMetricMachineData)
    fun machineExist(id: Int): Boolean
}