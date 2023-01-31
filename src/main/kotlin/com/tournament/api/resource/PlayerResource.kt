package com.tournament.api.resource

import com.tournament.api.dto.PlayerDTO
import com.tournament.domain.service.PlayerService
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val playerService: PlayerService by inject()

    routing {
        route("/tournament") {

            get("/player/all") {
                call.respond(status = OK, playerService.findAll())
            }

            get("/player/{id}") {
                try {
                    val id = call.parameters["id"]?.toLong()
                    val player = playerService.findPlayerById(id!!)
                    if (player != null)
                        call.respond(status = OK, player)
                    else
                        call.respond(status = NotFound, "player $id not found")
                } catch (e: Exception) {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            post("/player") {
                val nickname = call.receiveParameters()["nickname"]

                if (nickname != null && nickname.length <= 50) {
                    val player = playerService.save(PlayerDTO(nickname))
                    call.respond(status = Created, player)
                } else {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            put("/player/{id}") {
                try {
                    val id = call.parameters["id"]?.toLong()
                    val points = call.receiveParameters()["points"]?.toInt()
                    val player = playerService.updatePoints(id!!, points!!)
                    call.respond(status = OK, player)
                } catch (e: Exception) {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            delete("/player/{id}") {
                try {
                    val id = call.parameters["id"]?.toLong()
                    playerService.delete(id!!)
                    call.respond(status = OK, "player $id deleted")
                } catch (e: Exception) {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            delete("/player/all") {
                playerService
                    .findAll()
                    .forEach { player ->
                        playerService.delete(player.id)
                    }
                call.respond(status = OK, "all players have been deleted")
            }
        }
    }
}