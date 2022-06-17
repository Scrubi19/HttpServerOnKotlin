package com.kotlinhttpserver.backend.service

import com.kotlinhttpserver.backend.model.Book
import com.kotlinhttpserver.backend.model.BookDAO
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(private val books: BookDAO) {

    fun getAll (): MutableIterable<Book> {
        return books.findAll()
    }

    fun getById (id : Long): Optional<Book> {
        return books.findById(id)
    }

    fun getByName (name: String): List<Book> {
        return books.findByNameContaining(name)
    }

    fun getByAuthor (author: String): List<Book> {
        return books.findByAuthorContaining(author)
    }

    fun getByGenre (genre: String): List<Book> {
        return books.findByGenre(genre)
    }

    fun add (book : Book): Book {
       return books.save(book)
    }

    fun edit (id : Long, book : Book): Book {
        return books.save(book.copy(id = id))
    }

    fun deleteById (id: Long) = books.deleteById(id)

    fun existById (id: Long): Boolean {
        return books.existsById(id)
    }
}