package org.thechance.service_identity.utils

import io.ktor.http.*
import io.ktor.server.application.*
import org.thechance.service_identity.api.ServerResponse

fun handleException(cause: Throwable, call: ApplicationCall) {

    val messageResponse = when (cause) {
        is IdNotFoundException -> {
            ServerResponse.error(errorMessage = "Id Not Found", code = HttpStatusCode.NotFound.value)
        }

        is InvalidIdException -> {
            ServerResponse.error(errorMessage = "Invalid Id", code = HttpStatusCode.BadRequest.value)
        }

        else -> {
            ServerResponse.error(cause.message.toString(), HttpStatusCode.InternalServerError.value)
        }
    }

}