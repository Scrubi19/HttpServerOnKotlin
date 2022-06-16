package com.kotlinhttpserver.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
	println("Starting application...")
	runApplication<BackendApplication>(*args)
}
