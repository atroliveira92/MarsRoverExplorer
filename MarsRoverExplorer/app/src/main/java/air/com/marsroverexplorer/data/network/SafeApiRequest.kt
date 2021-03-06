package air.com.marsroverexplorer.data.network

import air.com.marsroverexplorer.util.ApiException
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            message.append(error)
            message.append("\n")
            message.append("Error code ${response.code()}")

            throw ApiException(message.toString(), response.code())
        }
    }
}