package com.carbontower.application.web.exceptions

import com.carbontower.application.web.CarbonTowerException


abstract class Unauthorized : CarbonTowerException(){
    abstract override var message: String
    override var statusCode: Int = 401
    abstract override var messageLog: String
}