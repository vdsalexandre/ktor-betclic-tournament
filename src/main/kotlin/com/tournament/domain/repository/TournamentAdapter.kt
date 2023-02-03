package com.tournament.domain.repository

import com.tournament.api.dto.PlayerDTO
import com.tournament.api.dto.PlayerDTO.Companion.toDto
import com.tournament.domain.model.Player

class TournamentAdapter : Tournament {
    override fun setRanking(players: List<Player>): List<PlayerDTO> {
        var rankingPlayer = players.first().toDto(1)

        return players.map { player ->
            if (rankingPlayer.points == player.points) {
                rankingPlayer = player.toDto(rankingPlayer.ranking)
                rankingPlayer
            } else {
                rankingPlayer = player.toDto(rankingPlayer.ranking + 1)
                rankingPlayer
            }
        }
    }
}