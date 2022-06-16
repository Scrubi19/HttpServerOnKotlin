package com.kotlinhttpserver.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name="books")
data class Book (
    @Id
    @Column(name = "id", nullable = false) // not null
    @GeneratedValue(strategy = GenerationType.AUTO) // autoIncrement
    @JsonProperty("id")
    val id: Long = 0L,

    @Column(name = "name", length = 100)
    @JsonProperty("name")
    val name : String,

    @Column(name = "author", length = 100)
    @JsonProperty("author")
    val author : String,

    @Column(name = "genre", length = 100)
    @JsonProperty("genre")
    val genre : String,

    @Column(name = "pages")
    @JsonProperty("pages")
    val pages : Int,

    @Column(name = "cost")
    @JsonProperty("cost")
    val cost : Int
)