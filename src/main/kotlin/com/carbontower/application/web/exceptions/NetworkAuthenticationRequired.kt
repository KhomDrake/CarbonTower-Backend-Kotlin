package com.carbontower.application.web.exceptions

import com.carbontower.application.web.CarbonTowerException


abstract class NetworkAuthenticationRequired : CarbonTowerException(){
    abstract override var message: String
    override var statusCode: Int = 511
    abstract override var messageLog: String
}