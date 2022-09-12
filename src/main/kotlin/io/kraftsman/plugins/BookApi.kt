package io.kraftsman.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.kraftsman.entities.Book
import io.kraftsman.responses.BookResponse
import io.kraftsman.tables.Books
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureBookApi() {
    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:mysql://localhost:8889/sample"
        driverClassName = "com.mysql.cj.jdbc.Driver"
        username = "root"
        password = "root"
        maximumPoolSize = 10
    }
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

    routing {
        get("/api/v1/books") {
            val books = newSuspendedTransaction {
                Book.find { Books.id lessEq 10 }
                    .orderBy(Books.id to SortOrder.DESC)
                    .map {
                        BookResponse(
                            title = it.title,
                            author = it.author.name,
                            genre = it.genre,
                            isbn = it.isbn,
                            publisher = it.publisher,
                        )
                    }
            }

            call.respond(mapOf("data" to books))
        }
    }
}
