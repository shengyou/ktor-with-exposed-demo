package io.kraftsman

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.kraftsman.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
        configureBookApi()
    }.start(wait = true)
}
