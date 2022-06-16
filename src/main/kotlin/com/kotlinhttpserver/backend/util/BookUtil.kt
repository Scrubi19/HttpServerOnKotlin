package com.kotlinhttpserver.backend.util

import com.kotlinhttpserver.backend.model.Book

class BookUtil {
    companion object {
        const val MAX_LENGTH : Int = 100

        fun bookValidate(book : Book): Boolean {
            if (book.name.isEmpty() || book.genre.isEmpty()) {
                return false
            } else if (book.cost < 0) {
                return false;
            }
            return true
        }
    }

}