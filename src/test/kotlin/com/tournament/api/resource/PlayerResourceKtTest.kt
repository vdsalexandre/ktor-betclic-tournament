package com.tournament.api.resource

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HeaderValueParam
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import org.junit.jupiter.api.Test

class PlayerResourceKtTest {

    @Test
    fun `should respond 200 OK when calling API endpoint find all players of the tournament`() {
        testApplication {
            client.get("/tournament/players").apply {
                assertThat(status).isEqualTo(HttpStatusCode.OK)
                assertThat(contentType()).isEqualTo(ContentType("application", "json", listOf(HeaderValueParam("charset", "UTF-8"))))
            }
        }
    }

    @Test
    fun `should respond 200 OK when calling API endpoint find player by id`() {
        testApplication {
            client.get("/tournament/players/1").apply {
                assertThat(status).isEqualTo(HttpStatusCode.OK)
                assertThat(contentType()).isEqualTo(ContentType("application", "json", listOf(HeaderValueParam("charset", "UTF-8"))))
            }
        }
    }

    @Test
    fun `should respond 400 bad request when calling API endpoint find player by id with wrong parameter`() {
        testApplication {
            client.get("/tournament/players/toto").apply {
                assertThat(status).isEqualTo(HttpStatusCode.BadRequest)
            }
        }
    }
}