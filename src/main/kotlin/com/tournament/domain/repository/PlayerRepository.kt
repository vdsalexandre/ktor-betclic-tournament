package com.tournament.domain.repository

import com.tournament.api.dto.PlayerDTO
import com.tournament.domain.model.Player

interface PlayerRepository {
    fun findById(id: Long): Player?
    fun findAll(): List<Player>
    fun save(playerDTO: PlayerDTO): Player
    fun delete(id: Long)
    fun update(id: Long, points: Int): Player
}