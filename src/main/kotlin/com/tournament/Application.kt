package com.tournament

import com.tournament.api.resource.configureRouting
import com.tournament.bootstrap.configureKoin
import com.tournament.domain.config.configureDatabase
import com.tournament.domain.config.configureMonitoring
import com.tournament.domain.config.configureSerialization
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureKoin()
    configureSerialization()
    configureMonitoring()
    configureDatabase(environment.config)
    configureRouting()
}
