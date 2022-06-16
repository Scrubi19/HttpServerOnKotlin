package com.kotlinhttpserver.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
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
    @field:NotNull(message = "{validation.name.notNull}")
    @field:NotBlank(message = "{validation.name.notBlank}")
    @field:Size(max=100, message = "{validation.name.size}")
    val name : String,

    @Column(name = "author", length = 100)
    @JsonProperty("author")
    @field:NotNull(message = "{validation.author.notNull}")
    @field:NotBlank(message = "{validation.author.notBlank}")
    @field:Size(max=100, message = "{validation.author.size}")
    val author : String,

    @Column(name = "genre", length = 100)
    @JsonProperty("genre")
    @field:NotNull(message = "{validation.genre.notNull}")
    @field:NotBlank(message = "{validation.genre.notBlank}")
    @field:Size(max=100, message = "{validation.genre.size}")
    val genre : String,

    @Column(name = "pages")
    @JsonProperty("pages")
    @field:NotNull(message = "{validation.pages.notNull}")
    @field:Min(0, message = "{validation.pages.notNegative}")
    val pages : Int,

    @Column(name = "cost")
    @JsonProperty("cost")
    @field:NotNull(message = "{validation.cost.notNull}")
    @field:Min(0, message = "{validation.cost.notNegative}")
    val cost : Int
)