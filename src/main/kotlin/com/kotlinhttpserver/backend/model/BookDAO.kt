package com.kotlinhttpserver.backend.model

import org.springframework.data.repository.CrudRepository

interface BookDAO : CrudRepository <Book, Long> {
    fun findByOrderByName(): List<Book>

    fun findByOrderByAuthor(): List<Book>

    fun findByOrderByGenre(): List<Book>
}