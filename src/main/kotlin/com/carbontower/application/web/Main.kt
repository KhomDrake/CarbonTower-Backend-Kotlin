package com.carbontower.application.web

import com.carbontower.domain.entities.application.LogApplication
import com.carbontower.domain.entities.database.T_LOGS_SERVER
import com.carbontower.resources.database.exception.HasNoCookie
import io.javalin.Context
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val carbonTower = CarbonTower()
    carbonTower.startServer()
}

internal fun toJson(block: (Context) -> Any): (Context) -> Unit{
    return {ctx: Context -> ctx.json(block(ctx))}
}

fun Context.insertLogSuccess(message: String) {
    val ctx = this
    transaction {
        T_LOGS_SERVER.insert {
            it[T_LOGS_SERVER.message] = message
            it[T_LOGS_SERVER.router] = ctx.path()
            it[T_LOGS_SERVER.statusCode] = 200
            it[T_LOGS_SERVER.dateTime] = LocalDateTime.now().toString()
            it[T_LOGS_SERVER.method] = ctx.method()
            it[T_LOGS_SERVER.server] = "Carbon Tower"
        }
    }
}

fun Context.insertLogError(logApplication: LogApplication) {
    val ctx = this
    transaction {
        T_LOGS_SERVER.insert {
            it[T_LOGS_SERVER.message] = logApplication.message
            it[T_LOGS_SERVER.router] = logApplication.router
            it[T_LOGS_SERVER.statusCode] = logApplication.statusCode
            it[T_LOGS_SERVER.dateTime] = LocalDateTime.now().toString()
            it[T_LOGS_SERVER.method] = ctx.method()
            it[T_LOGS_SERVER.server] = "Carbon Tower"
        }
    }
}

fun Context.validateCookieAndReturnIdUser(cookie: Cookie) : String {
    validateCookie(cookie)
    val c = cookie(cookie.cookieName)
    return cookie.getIdCookie(c.toString())
}

fun Context.validateCookie(cookie: Cookie) {
    if(cookie.contains(this.cookie(cookie.cookieName)).not()) throw HasNoCookie()
}