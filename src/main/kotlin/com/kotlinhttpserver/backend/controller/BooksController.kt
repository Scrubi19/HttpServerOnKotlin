package com.kotlinhttpserver.backend.controller

import com.kotlinhttpserver.backend.model.Book
import com.kotlinhttpserver.backend.service.BookService
import com.kotlinhttpserver.backend.util.BookUtil
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/books")
class BooksController (private val bookService : BookService ) {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class BookByIdNotFoundException(id: Long): RuntimeException("Book with id = $id not found")

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class BookByStrNotFoundException(str: String): RuntimeException("Book with attribute = $str not found")

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    class BookMediaTypeException(): RuntimeException("Book have incorrect attribute")

    @GetMapping
    fun findAll(): MutableIterable<Book> {
        return bookService.getAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun readById(@Validated @PathVariable id: Long): Optional<Book> {
        if (bookService.existById(id)) {
            return bookService.getById(id)
        } else {
            throw BookByIdNotFoundException(id)
        }
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    fun readByName (@Validated @PathVariable name: String): List<Book> {
        if (name.isNotEmpty()) {
            println("book: "+bookService.getByName(name))
            return bookService.getByName(name)
        } else {
            throw BookByStrNotFoundException(name)
        }
    }
    // POST
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create (@Validated @RequestBody book : Book): Book {
        if (!BookUtil.bookValidate(book)) {
            throw BookMediaTypeException()
        } else {
            return bookService.add(book)
        }
    }

    // PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun update (@Validated @PathVariable id : Long, @Validated @RequestBody book : Book ): Book {
        if (!bookService.existById(id)) {
            throw BookByIdNotFoundException(id)
        } else if (!BookUtil.bookValidate(book)) {
            throw BookMediaTypeException()
        } else {
            return bookService.edit(id, book)
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    fun delete (@Validated @PathVariable id : Long) {
        if (bookService.existById(id)) {
            bookService.deleteById(id)
        } else {
            throw BookByIdNotFoundException(id)
        }
    }
}