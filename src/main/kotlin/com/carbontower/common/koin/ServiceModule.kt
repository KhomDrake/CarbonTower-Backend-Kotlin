package com.carbontower.common.koin

import com.carbontower.domain.services.championship.ChampionshipService
import com.carbontower.domain.services.data.DataService
import com.carbontower.domain.services.home.HomeService
import com.carbontower.domain.services.login.LoginService
import com.carbontower.domain.services.player.PlayerService
import com.carbontower.domain.services.signup.SignupService
import org.koin.dsl.module.module

val serviceModule = module {
    single { ChampionshipService(get()) }
    single { DataService(get()) }
    single { HomeService(get()) }
    single { LoginService(get(), get(), get()) }
    single { PlayerService(get()) }
    single { SignupService(get(), get(), get()) }
}