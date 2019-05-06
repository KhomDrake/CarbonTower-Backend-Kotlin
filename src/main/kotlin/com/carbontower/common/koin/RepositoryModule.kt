package com.carbontower.common.koin

import com.carbontower.domain.services.championship.ChampionshipRepository
import com.carbontower.domain.services.championship.IChampionshipRepository
import com.carbontower.domain.services.data.DataRepository
import com.carbontower.domain.services.data.IDataRepository
import com.carbontower.domain.services.home.HomeRepository
import com.carbontower.domain.services.home.IHomeRepository
import com.carbontower.domain.services.login.ILoginRepository
import com.carbontower.domain.services.login.LoginRepository
import com.carbontower.domain.services.player.IPlayerRepository
import com.carbontower.domain.services.player.PlayerRepository
import com.carbontower.domain.services.signup.ISignupRepository
import com.carbontower.domain.services.signup.SignupRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    single { ChampionshipRepository() as IChampionshipRepository }
    single { DataRepository() as IDataRepository }
    single { HomeRepository() as IHomeRepository }
    single { LoginRepository() as ILoginRepository }
    single { PlayerRepository() as IPlayerRepository }
    single { SignupRepository() as ISignupRepository }
}
