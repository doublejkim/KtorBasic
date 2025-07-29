package com.example

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime


data class Sample(
    val name: String,
    val createdAt: LocalDateTime,
)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/sample") {
            call.respond(Sample(name = "Hello", createdAt = LocalDateTime.now()))
        }

        post("/sample") {
            call.receive<Sample>().let {
                call.respond(Sample(name = it.name, createdAt = LocalDateTime.now() ))
            }
        }
    }
}
