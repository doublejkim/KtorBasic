package com.example

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


fun Application.configureSerialization() {

    val javaTimeModule = JavaTimeModule().apply {
        addSerializer(LocalDateTime::class.java, object : JsonSerializer<LocalDateTime>(){
            override fun serialize(
                value: LocalDateTime,
                gen: JsonGenerator,
                serializers: SerializerProvider
            ) {
                gen.writeString(
                    value.truncatedTo(ChronoUnit.SECONDS).atZone(ZoneId.systemDefault()).format(
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                    )
                )
            }

        })

        addDeserializer(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime>(){
            override fun deserialize(
                parser: JsonParser,
                ctxt: DeserializationContext
            ): LocalDateTime? {
                return LocalDateTime.parse(parser.valueAsString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }

        })

    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(javaTimeModule)
            propertyNamingStrategy = PropertyNamingStrategies.LowerCamelCaseStrategy()
        }
    }

    routing {
        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
