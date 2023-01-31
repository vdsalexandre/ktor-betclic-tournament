package com.tournament.domain.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Players : LongIdTable() {
    val nickname = varchar(name = "nickname", length = 50)
    val points = integer(name = "points")
}

class PlayerEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PlayerEntity>(Players)

    var nickname by Players.nickname
    var points by Players.points

    fun toPlayer() = Player(id = id.value, nickname = nickname, points = points)
}

@Serializable
data class Player(val id: Long, val nickname: String, val points: Int)