package com.carbontower.application.web

import io.javalin.Context

fun main(args: Array<String>) {
    val carbonTower = CarbonTower()
    carbonTower.startServer()
}

internal fun toJson(block: (Context) -> Any): (Context) -> Unit{
    return {ctx: Context -> ctx.json(block(ctx))}
}