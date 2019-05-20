package com.carbontower.application.web.controllers

import com.carbontower.application.web.Cookie
import com.carbontower.application.web.toJson
import com.carbontower.domain.entities.http.InsertMachineData
import com.carbontower.domain.entities.http.InsertMetricMachineData
import com.carbontower.domain.entities.response.MachineData
import com.carbontower.domain.services.machine.MachineService
import io.javalin.Context
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get

class MachineController(private val machineService: MachineService,
                        private val cookie: Cookie) {
    fun routes() {
        path("/machine") {
            post("/", toJson { insertMachine(it) })
            get("/", toJson { getMachines(it) })
            get(":id", toJson { getMachine(it) })
            post("/metric/:id", toJson { insertMachineMetric(it) })
            get("/all-metrics", toJson {  })
        }
    }

    private fun insertMachineMetric(ctx: Context) : Boolean {
        val insertMetricMachineData = ctx.body<InsertMetricMachineData>()
        machineService.insertMachineMetric(ctx.pathParam("id").toInt(), insertMetricMachineData)
        return true
    }

    private fun getMachine(ctx: Context) : MachineData {
        return machineService.getMachine(ctx.pathParam("id").toInt())
    }

    private fun getMachines(ctx: Context) : List<MachineData> {
        return machineService.getAllMachines()
    }

    private fun insertMachine(ctx: Context) : Boolean {
        val c = ctx.cookie(cookie.cookieName)
        val idUser: String = cookie.getIdCookie(c.toString())
        val insertMachineData = ctx.body<InsertMachineData>()
        machineService.insertMachine(idUser, insertMachineData)
        return true
    }
}