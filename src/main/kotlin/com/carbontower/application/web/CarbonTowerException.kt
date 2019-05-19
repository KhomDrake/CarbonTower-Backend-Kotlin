package com.carbontower.application.web

import java.lang.Exception

abstract class CarbonTowerException : Exception() {
    abstract override var message: String
    abstract var statusCode: Int
}