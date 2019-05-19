package com.carbontower.application.web

import io.javalin.Context
import java.lang.Exception

object HandlerError{
    fun handleListaDeFavorecidosException(ctx: Context, exception: CarbonTowerException) {
        ctx.status(exception.statusCode)
        ctx.json(exception.message)
    }

    fun anyOtherError(ctx: Context, exception: Exception) {
        ctx.status(statusCode = 500)
        ctx.json(exception.message.toString())
    }
}