package data.gateway.remote

import data.remote.model.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.HttpResponse
import presentation.base.InternetException
import presentation.base.InvalidCredentialsException
import presentation.base.NoInternetException
import presentation.base.UnknownErrorException
import presentation.base.UserNotFoundException

abstract class BaseRemoteGateway(val client: HttpClient) {

   protected suspend inline fun <reified T> tryToExecute(
        method: HttpClient.() -> HttpResponse
    ): T {
        try {
            return client.method().body<T>()
        } catch (e: ClientRequestException) {
            val errorMessages = e.response.body<BaseResponse<*>>().status.errorMessages
            errorMessages?.let { throwMatchingException(it) }
            throw UnknownErrorException()
        } catch (e: InternetException) {
            throw NoInternetException()
        } catch (e: Exception) {
            throw UnknownErrorException()
        }
    }

    fun throwMatchingException(errorMessages: Map<String, String>) {
        if (errorMessages.containsErrors("1013")) {
            throw InvalidCredentialsException()
        } else if (errorMessages.containsErrors("1043")) {
            throw UserNotFoundException(errorMessages["1043"] ?: "")
        } else {
            throw UnknownErrorException()
        }
    }

    private fun Map<String, String>.containsErrors(vararg errorCodes: String): Boolean =
        keys.containsAll(errorCodes.toList())

}