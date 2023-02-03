package com.tournament.api.dto

import com.tournament.domain.model.Player
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDTO(val id: Long, val nickname: String, val points: Int, val ranking: Int) {

    companion object {
        fun Player.toDto(ranking: Int) = PlayerDTO(id, nickname, points, ranking)
    }
}
