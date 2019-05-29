package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.insertLogSuccess
import com.carbontower.application.web.toJson
import com.carbontower.application.web.validateCookie
import com.carbontower.domain.entities.http.DateMetricMachineData
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData
import com.carbontower.domain.entities.response.MachineMetricData
import com.carbontower.domain.services.machine.MachineService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get

class MachineController(private val machineService: MachineService,
                        private val cookie: Cookie) {
    fun routes() {
        path("/machine") {
            post("/:iduser", toJson { insertMachine(it) })
            get("/", toJson { getMachines(it) })
            get(":id", toJson { getMachine(it) })
            post("/metric/:id", toJson { insertMachineMetric(it) })
            post("/metric/by-date/:id", toJson { getMetricMachineByDate(it) })
        }
    }

    private fun getMetricMachineByDate(ctx: Context) : List<MachineMetricData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val idMachine = ctx.pathParam("id")
        val dateMetricMachineData = ctx.body<DateMetricMachineData>()
        val listMachinesMetric = machineService.getMachineMetricByDate(idMachine, dateMetricMachineData)
        ctx.insertLogSuccess("Metricas da máquina $idMachine solicitados pelo usuário $idUser")
        return listMachinesMetric
    }

    private fun insertMachineMetric(ctx: Context) : Boolean {
        val insertMetricMachineData = ctx.body<InsertMetricMachineData>()
        machineService.insertMachineMetric(ctx.pathParam("id"), insertMetricMachineData)
        ctx.insertLogSuccess("Foi inserido metrica da máquina ${ctx.pathParam("id")} com sucesso. $insertMetricMachineData")
        return true
    }

    private fun getMachine(ctx: Context) : MachineData {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val machineData = machineService.getMachine(ctx.pathParam("id"))
        ctx.insertLogSuccess("Usuário $idUser solicitou dados da machina ${ctx.pathParam("id")}" +
                " retorno: $machineData")
        return machineData
    }

    private fun getMachines(ctx: Context) : List<MachineData> {
        ctx.validateCookie(cookie)
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val allMachines = machineService.getAllMachines()
        ctx.insertLogSuccess("Usuário $idUser solicitou todas as máquinas")
        return allMachines
    }

    private fun insertMachine(ctx: Context) : Boolean {
        val idUser: String = ctx.pathParam("iduser")
        val insertMachineData = ctx.body<InsertMachineData>()
        machineService.createMachine(idUser, insertMachineData)
        ctx.insertLogSuccess("Usuário $idUser inseriu uma máquina com os dados: $insertMachineData")
        return true
    }
}