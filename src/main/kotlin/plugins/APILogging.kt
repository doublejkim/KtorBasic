package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*
import org.slf4j.LoggerFactory

fun Application.callLogging() {
    install(CallMonitoringPlugin)
}

val CallMonitoringPlugin = createApplicationPlugin(name = "CallMonitoringPlugin") {

    val log = LoggerFactory.getLogger("CallLogging")

    onCall { call ->

        if (call.request.path().startsWith("/api")) {
            val start = System.currentTimeMillis() / 1000
            call.attributes.put(AttributeKey("start-time"), start)
        }

    }

    onCallRespond { call, body ->

        if (call.request.path().startsWith("/api")) {
            val start = call.attributes[AttributeKey("start-time")] as Long
            val duration = System.currentTimeMillis() / 1000 - start
            val path = call.request.path()

            log.info("API Status: [${call.response.status()}] [$path] [$body] [$duration]")
        }

    }
}