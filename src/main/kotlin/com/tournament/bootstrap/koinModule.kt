package com.tournament.bootstrap

import com.tournament.domain.repository.PlayerRepositoryAdapter
import com.tournament.domain.service.PlayerService
import org.koin.dsl.module

val koinModule = module {
    single { PlayerService(get()) }
    single { PlayerRepositoryAdapter() }
}
