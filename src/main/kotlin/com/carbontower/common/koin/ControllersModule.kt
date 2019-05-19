package com.carbontower.common.koin

import com.carbontower.application.web.controllers.*
import org.koin.dsl.module.module

val controllerModule = module {
    single { ChampionshipController(get()) }
    single { DataController(get(), get()) }
    single { HomeController(get()) }
    single { LoginController(get(), get()) }
    single { PlayerController(get(), get()) }
    single { SignupController(get(), get()) }
}