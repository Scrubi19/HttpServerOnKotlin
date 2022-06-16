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

    @GetMapping
    fun findAll(): MutableIterable<Book> {
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
            println("name: $name")
            return bookService.getByName(name)
    }
    // POST
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create (@Valid @RequestBody book : Book): Book {
        return bookService.add(book)
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