package com.tournament.domain.service

import com.tournament.api.dto.PlayerDTO
import com.tournament.domain.model.Player
import com.tournament.domain.repository.TournamentAdapter
import org.koin.core.component.KoinComponent

class TournamentService(private val tournamentAdapter: TournamentAdapter) : KoinComponent {

    fun setRanking(players: List<Player>): List<PlayerDTO> = tournamentAdapter.setRanking(players)
}