package com.kotlinhttpserver.backend.service

import com.kotlinhttpserver.backend.model.Book
import com.kotlinhttpserver.backend.model.BookDAO
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(private val books: BookDAO) {

    fun getAll (): Iterable<Book> {
        return books.findAll()
    }

    fun getById (id : Long): Optional<Book> {
        return books.findById(id)
    }

    fun getByName (name: String) : List <Book> {
        return books.findByOrderByName()
    }

    fun getByAuthor (author: String) : List <Book> {
        return books.findByOrderByAuthor()
    }

    fun getByGenre (genre: String) : List <Book> {
        return books.findByOrderByGenre()
    }

    fun add (book : Book) : Book {
        return books.save(book)
    }

    fun edit (id : Long, book : Book) : Book {
        return books.save(book.copy(id = id))
    }

    fun deleteById (id: Long) {
        return books.deleteById(id)
    }


}