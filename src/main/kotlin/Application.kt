package com.example

import com.example.plugins.callLogging
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    //configureDatabases()
    callLogging()
    configureRouting()
}
