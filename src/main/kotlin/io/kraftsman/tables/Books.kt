package io.kraftsman.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Books : IntIdTable(name = "books") {
    val title = varchar("title", 255)
    val genre = varchar("genre", 255)
    val isbn = varchar("isbn", 13)
    val publisher = varchar("publisher", 255)
    val author = reference("author", Authors)
}
