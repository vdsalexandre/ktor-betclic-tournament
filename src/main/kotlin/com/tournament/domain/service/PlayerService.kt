package com.tournament.domain.service

import com.tournament.domain.repository.PlayerRepositoryAdapter
import org.koin.core.component.KoinComponent

class PlayerService(private val playerRepositoryAdapter: PlayerRepositoryAdapter) : KoinComponent {

    fun findPlayerById(id: Long) = playerRepositoryAdapter.findById(id)

    fun findAll() = playerRepositoryAdapter.findAll()

    fun save(nickname: String, points: Int = 0) = playerRepositoryAdapter.save(nickname, points)

    fun delete(id: Long) = playerRepositoryAdapter.delete(id)

    fun updatePoints(id: Long, points: Int) = playerRepositoryAdapter.update(id, points)
}