package com.carbontower.common.koin

import com.carbontower.application.web.Cookie
import com.carbontower.domain.services.DataValidation
import com.carbontower.domain.services.EncryptCookie
import com.carbontower.resources.database.Connection
import com.carbontower.resources.Encrypt
import org.koin.dsl.module.module

val auxiliaryModule = module {
    single { Encrypt() }
    single { EncryptCookie(get()) }
    single { Connection() }
    single { Cookie(get()) }
    single { DataValidation() }
}
