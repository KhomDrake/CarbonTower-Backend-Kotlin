package com.carbontower.application.web.controllers

import com.carbontower.application.web.toJson
import com.carbontower.domain.services.data.DataService
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

class DataController(private val dataService: DataService) {
    fun routes() {
        path("/data") {
            get("machine/:idUserRole/graphics/:idChampionship/latest", toJson {  })
            get("machine/:idUserRole/graphics/:idChampionship/last", toJson {  })
        }
    }
}