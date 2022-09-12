package io.kraftsman.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun Application.configureBookApi() {
    Database.connect(
        url = "jdbc:mysql://localhost:8889/sample",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root"
    )

    routing {
        get("/api/v1/books") {
            call.respond(mapOf("data" to "books"))
        }
    }
}
