package com.tournament.bootstrap

import com.tournament.domain.repository.PlayerRepositoryAdapter
import com.tournament.domain.service.PlayerService
import io.ktor.server.application.Application
import org.koin.dsl.module
import org.koin.ktor.plugin.koin


fun Application.configureKoin() {
    val koinModule = module {
        single { PlayerService(get()) }
        single { PlayerRepositoryAdapter() }
    }

    koin {
        modules(koinModule)
    }
}