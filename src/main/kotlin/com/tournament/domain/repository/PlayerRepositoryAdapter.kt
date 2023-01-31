package com.tournament.domain.repository

import com.tournament.domain.model.Player
import com.tournament.domain.model.PlayerEntity
import org.jetbrains.exposed.sql.transactions.transaction

class PlayerRepositoryAdapter : PlayerRepository {

    override fun findById(id: Long): Player? = transaction {
        PlayerEntity.findById(id)?.toPlayer()
    }

    override fun findAll(): List<Player> = transaction {
        PlayerEntity.all().map { it.toPlayer() }
    }

    override fun save(nickname: String, points: Int) = transaction {
        PlayerEntity.new {
            this.nickname = nickname
            this.points = points
        }.toPlayer()
    }

    override fun delete(id: Long) = transaction {
        PlayerEntity[id].delete()
    }

    override fun update(id: Long, points: Int) = transaction {
        PlayerEntity[id].points = points

        return@transaction PlayerEntity[id].toPlayer()
    }
}