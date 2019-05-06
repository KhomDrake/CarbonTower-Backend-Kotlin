package com.carbontower.application.web

import com.carbontower.application.web.controllers.*
import com.carbontower.common.koin.controllerModule
import com.carbontower.common.koin.repositoryModule
import com.carbontower.common.koin.serviceModule
import io.javalin.Javalin
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject

class CarbonTower : KoinComponent {
    private lateinit var javalin: Javalin

    private val championshipController: ChampionshipController by inject()
    private val dataController: DataController by inject()
    private val homeController: HomeController by inject()
    private val loginController: LoginController by inject()
    private val playerController: PlayerController by inject()
    private val signupController: SignupController by inject()

    fun startServer() {
        StandAloneContext.startKoin(listOf(repositoryModule, serviceModule, controllerModule))
        javalin = Javalin.create()
        javalin.apply {
            enableStaticFiles("/public")
//            exception(Exception::class.java, Handler())
            routes {
                championshipController.routes()
                dataController.routes()
                homeController.routes()
                loginController.routes()
                playerController.routes()
                signupController.routes()
            }
        }

        javalin.start(3000)

    }

    fun stopServer() {
        javalin.stop()
    }
}