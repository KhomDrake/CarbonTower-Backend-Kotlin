package com.carbontower.domain.entities.application

data class LogApplicationDb(val router: String, val statusCode: Int, val message: String, val dateTime: String, val method: String, val server: String)