package com.tournament.domain.repository

import com.tournament.domain.model.Player

interface PlayerRepository {
    fun findById(id: Long): Player?
    fun findAll(): List<Player>
    fun findAllSorted(): List<Player>
    fun save(nickname: String, points: Int): Player
    fun delete(id: Long)
    fun update(id: Long, points: Int): Player
}