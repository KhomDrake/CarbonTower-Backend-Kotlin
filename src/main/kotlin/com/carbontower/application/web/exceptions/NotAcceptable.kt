package com.carbontower.application.web.exceptions

import com.carbontower.application.web.CarbonTowerException


abstract class NotAcceptable : CarbonTowerException(){
    abstract override var message: String
    override var statusCode: Int = 406
    abstract override var messageLog: String
}