package com.carbontower.application.web.exceptions

import com.carbontower.application.web.CarbonTowerException

abstract class UnprocessableEntity : CarbonTowerException(){
    abstract override var message: String
    override var statusCode: Int = 422
}