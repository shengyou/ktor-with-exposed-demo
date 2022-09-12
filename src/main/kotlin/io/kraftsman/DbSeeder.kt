package io.kraftsman

import com.github.javafaker.Faker
import io.kraftsman.entities.Author
import io.kraftsman.entities.Book
import io.kraftsman.tables.Authors
import io.kraftsman.tables.Books
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect(
        url = "jdbc:mysql://localhost:8889/sample",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root"
    )

    transaction {
        SchemaUtils.drop(
            Books,
            Authors,
        )

        SchemaUtils.create(
            Books,
            Authors,
        )
    }

    val faker = Faker()

    transaction {
        val fixedAuthor = Author.new {
            name = faker.book().author()
        }

        repeat(20) {

            val newAuthor = if (it in listOf(1, 3, 5, 7, 9)) {
                fixedAuthor
            } else {
                Author.new {
                    name = faker.book().author()
                }
            }

            Book.new {
                title = faker.book().title()
                genre = faker.book().genre()
                isbn = faker.code().isbn13()
                publisher = faker.book().publisher()
                author = newAuthor
            }
        }
    }
}
