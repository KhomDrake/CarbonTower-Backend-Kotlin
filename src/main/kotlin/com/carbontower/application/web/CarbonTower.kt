package com.carbontower.application.web

import com.carbontower.application.web.controllers.*
import com.carbontower.common.koin.auxiliaryModule
import com.carbontower.common.koin.controllerModule
import com.carbontower.common.koin.repositoryModule
import com.carbontower.common.koin.serviceModule
import com.carbontower.domain.entities.database.*
import com.carbontower.domain.entities.response.InviteTotalData
import com.carbontower.domain.entities.response.Match
import com.carbontower.domain.entities.response.Time
import com.carbontower.domain.entities.response.UserData
import com.carbontower.resources.database.Connection
import io.javalin.ExceptionHandler
import io.javalin.Javalin
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.countDistinct
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import java.lang.Exception

class CarbonTower : KoinComponent {
    private lateinit var javalin: Javalin

    private val championshipController: ChampionshipController by inject()
    private val dataController: DataController by inject()
    private val loginController: LoginController by inject()
    private val playerController: PlayerController by inject()
    private val signupController: SignupController by inject()
    private val machineController: MachineController by inject()
    private val connection: Connection by inject()

    fun startServer() {
        StandAloneContext.startKoin(listOf(auxiliaryModule ,repositoryModule, serviceModule, controllerModule))
        connection.connectWithCarbonTowerDatabase()

        javalin = Javalin.create()
        javalin.apply {
            enableStaticFiles("/public")
            exception(Exception::class.java) { e, ctx ->
                when(e) {
                    is CarbonTowerException -> HandlerError.handleCarbonTowerException(ctx, e)
                    else ->  HandlerError.anyOtherError(ctx, e)
                }
            }
            enableCorsForAllOrigins()
            routes {
                championshipController.routes()
                dataController.routes()
                loginController.routes()
                playerController.routes()
                signupController.routes()
                machineController.routes()
            }
        }

        javalin.start(7000)

        javalin.after {
            ctx ->

            ctx.header("Access-Control-Allow-Credentials", "true")
        }
    }

    fun stopServer() {
        javalin.stop()
    }
}