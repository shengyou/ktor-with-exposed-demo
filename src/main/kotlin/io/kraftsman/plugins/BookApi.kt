package io.kraftsman.plugins

import io.kraftsman.entities.Book
import io.kraftsman.tables.Books
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureBookApi() {
    Database.connect(
        url = "jdbc:mysql://localhost:8889/sample",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root"
    )

    routing {
        get("/api/v1/books") {
            newSuspendedTransaction {
                Book.find { Books.id lessEq 10 }
                    .orderBy(Books.id to SortOrder.DESC)
            }

            call.respond(mapOf("data" to "books"))
        }
    }
}
