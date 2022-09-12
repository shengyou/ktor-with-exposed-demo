package io.kraftsman.responses

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val title: String,
    val author: String,
    val genre: String,
    val isbn: String,
    val publisher: String,
)
