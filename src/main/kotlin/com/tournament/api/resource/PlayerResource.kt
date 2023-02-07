package com.tournament.api.resource

import com.tournament.domain.service.PlayerService
import com.tournament.domain.service.TournamentService
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
    val tournamentService: TournamentService by inject()

    routing {
        route("/tournament") {

            get("/players") {
                call.respond(status = OK, playerService.findAll())
            }

            get("/players/sorted") {
                val sortedPlayers = playerService.findAllSorted()
                val playerDTOS = tournamentService.setRanking(sortedPlayers)
                call.respond(status = OK, playerDTOS)
            }

            get("/players/{id}/sorted") {
                try {
                    val id = call.parameters["id"]?.toLong()
                    val players = playerService.findAllSorted()
                    val playerDTO = tournamentService.setRanking(players).find { it.id == id }
                    if (playerDTO != null)
                        call.respond(status = OK, playerDTO)
                    else
                        call.respond(status = NotFound, "player $id not found")
                } catch (e: Exception) {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            get("/players/{id}") {
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

            post("/players") {
                val nickname = call.receiveParameters()["nickname"]

                if (nickname != null && nickname.length <= 50) {
                    val player = playerService.save(nickname)
                    call.respond(status = Created, player)
                } else {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            put("/players/{id}") {
                try {
                    val id = call.parameters["id"]?.toLong()
                    val points = call.receiveParameters()["points"]?.toInt()
                    val player = playerService.updatePoints(id!!, points!!)
                    call.respond(status = OK, player)
                } catch (e: Exception) {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            delete("/players/{id}") {
                try {
                    val id = call.parameters["id"]?.toLong()
                    playerService.delete(id!!)
                    call.respond(status = OK, "player $id deleted")
                } catch (e: Exception) {
                    call.respond(status = BadRequest, "bad request")
                }
            }

            delete("/players") {
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