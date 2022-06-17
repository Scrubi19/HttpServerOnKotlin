package com.kotlinhttpserver.backend.model

import org.springframework.data.repository.CrudRepository

interface BookDAO : CrudRepository <Book, Long> {
    fun findByNameContaining(name: String): List<Book>

    fun findByAuthorContaining(author : String): List<Book>

    fun findByGenre(genre : String): List<Book>
}