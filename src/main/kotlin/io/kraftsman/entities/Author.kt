package io.kraftsman.entities

import io.kraftsman.tables.Authors
import io.kraftsman.tables.Books
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Author(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Author>(Authors)

    var name by Authors.name

    val books by Book referrersOn Books.author
}
