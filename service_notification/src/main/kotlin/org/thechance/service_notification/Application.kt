package org.thechance.service_notification

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.thechance.service_notification.plugins.*
import org.thechance.service_notification.plugins.configureDependencyInjection

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureFirebaseApp()
    configureDependencyInjection()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}