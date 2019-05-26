package com.carbontower.application.web

import com.carbontower.domain.entities.application.LogApplication
import io.javalin.Context
import java.lang.Exception

object HandlerError{
    fun handleCarbonTowerException(ctx: Context, exception: CarbonTowerException) {
        ctx.insertLog(LogApplication(ctx.path(), exception.statusCode, exception.messageLog))
        ctx.status(exception.statusCode)
        ctx.json(exception.message)
    }

    fun anyOtherError(ctx: Context, exception: Exception) {
        ctx.insertLog(LogApplication(ctx.path(), 500, exception.message.toString()))
        ctx.status(statusCode = 500)
        ctx.json("Não foi possível processar esse pedido.")
    }
}