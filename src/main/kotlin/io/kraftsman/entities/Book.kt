package io.kraftsman.entities

import io.kraftsman.tables.Books
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Book(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Book>(Books)

    var title by Books.title
    var genre by Books.genre
    var isbn by Books.isbn
    var publisher by Books.publisher

    var author by Author referencedOn Books.author
}
