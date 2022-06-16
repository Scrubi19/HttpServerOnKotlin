package com.kotlinhttpserver.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name="books")
data class Book (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO) // autoIncrement
    @JsonProperty("id")
    @field:NotNull(message = "{validation.id.notNull}")
    val id: Long? = 0L,

    @Column(name = "name", length = 100)
    @JsonProperty("name")
    @field:NotNull
    @field:Size(max=100, message = "{validation.name.size}")
    val name : String,

    @Column(name = "author", length = 100)
    @JsonProperty("author")
    @field:NotNull
    @field:Size(max=100, message = "{validation.author.size}")
    val author : String,

    @Column(name = "genre", length = 100)
    @JsonProperty("genre")
    @field:NotNull
    @field:Size(max=100, message = "{validation.genre.size}")
    val genre : String,

    @Column(name = "pages")
    @JsonProperty("pages")
    @field:NotNull
    val pages : Int,

    @Column(name = "cost")
    @JsonProperty("cost")
    @field:NotNull
    val cost : Int
)