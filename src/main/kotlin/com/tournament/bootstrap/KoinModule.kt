package com.tournament.bootstrap

import com.tournament.domain.repository.PlayerRepositoryAdapter
import com.tournament.domain.repository.TournamentAdapter
import com.tournament.domain.service.PlayerService
import com.tournament.domain.service.TournamentService
import org.koin.dsl.module

object KoinModule {
    val koinModule = module {
        single { PlayerService(get()) }
        single { PlayerRepositoryAdapter() }

        single { TournamentService(get()) }
        single { TournamentAdapter() }
    }
}