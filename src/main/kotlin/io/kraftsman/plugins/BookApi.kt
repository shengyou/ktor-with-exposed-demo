package io.kraftsman.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureBookApi() {
    routing {
        get("/api/v1/books") {
            call.respond(mapOf("data" to "books"))
        }
    }
}
