package com.tournament.bootstrap

import com.tournament.bootstrap.KoinModule.koinModule
import io.ktor.server.application.Application
import org.koin.ktor.plugin.koin

fun Application.configureKoin() {
    koin {
        modules(koinModule)
    }
}