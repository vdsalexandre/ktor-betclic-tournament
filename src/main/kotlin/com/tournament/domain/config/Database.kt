package com.tournament.domain.config

import com.tournament.domain.model.PlayerEntity
import com.tournament.domain.model.Players
import io.ktor.server.application.Application
import io.ktor.server.config.ApplicationConfig
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase(config: ApplicationConfig) {
    val database = Database.connect(
        url = config.property("ktor.datasource.url").getString(),
        user = config.property("ktor.datasource.username").getString(),
        password = config.property("ktor.datasource.password").getString(),
        driver = config.property("ktor.datasource.driverClassName").getString()
    )

    transaction(database) {
        SchemaUtils.create(Players)

        PlayerEntity.new {
            nickname = "xXMillennialPlayerXx"
            points = 0
        }
        PlayerEntity.new {
            nickname = "ProPlayer1988"
            points = 0
        }
        PlayerEntity.new {
            nickname = "N00BNickname"
            points = 0
        }
    }
}