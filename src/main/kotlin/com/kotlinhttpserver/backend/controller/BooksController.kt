package com.kotlinhttpserver.backend.controller

import com.kotlinhttpserver.backend.model.Book
import com.kotlinhttpserver.backend.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/books")
class BooksController (private val bookService : BookService ) {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class BookByIdNotFoundException(id: Long): RuntimeException("Book with id = $id not found")

    // POST
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create (@Valid @RequestBody book : Book): Book {
        return bookService.add(book)
    }

    @GetMapping
    fun readAll(): MutableIterable<Book> {
        return bookService.getAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun readById(@Valid @PathVariable id: Long): Optional<Book> {
        if (bookService.existById(id)) {
            return bookService.getById(id)
        } else {
            throw BookByIdNotFoundException(id)
        }
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    fun readByName (@Valid @PathVariable name: String): List<Book> {
            return bookService.getByName(name)
    }

    @GetMapping("/author/{author}")
    @ResponseStatus(HttpStatus.FOUND)
    fun readByAuthor (@Valid @PathVariable author: String): List<Book> {
        return bookService.getByAuthor(author)
    }

    @GetMapping("/genre/{genre}")
    @ResponseStatus(HttpStatus.FOUND)
    fun readByGenre (@Valid @PathVariable genre: String): List<Book> {
        return bookService.getByGenre(genre)
    }

    // PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun update (@Valid @PathVariable id : Long, @Valid @RequestBody book : Book ): Book {
        if (!bookService.existById(id)) {
            throw BookByIdNotFoundException(id)
        } else {
            return bookService.edit(id, book)
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    fun delete (@Valid @PathVariable id : Long) {
        if (bookService.existById(id)) {
            bookService.deleteById(id)
        } else {
            throw BookByIdNotFoundException(id)
        }
    }
}