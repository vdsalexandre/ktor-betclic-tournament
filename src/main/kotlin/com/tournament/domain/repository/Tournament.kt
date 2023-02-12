package com.tournament.domain.repository

import com.tournament.api.dto.PlayerDTO
import com.tournament.domain.model.Player

interface Tournament {
    fun setRanking(players: List<Player>): List<PlayerDTO>
}