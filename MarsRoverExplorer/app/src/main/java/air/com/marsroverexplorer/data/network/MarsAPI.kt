package air.com.marsroverexplorer.data.network

import air.com.marsroverexplorer.model.manifest.PhotoManifestResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface MarsAPI {


    @GET("rovers/{rover}/photos?sol=1000&api_key=pkzzx9DLUejnkWlxoydv4aexzJhUFbMFHylBuy7p")
    fun listPhotosFromRover(@Path("rover") rover : String) : Call<ResponseBody>

    @GET("manifests/{rover}?&api_key=pkzzx9DLUejnkWlxoydv4aexzJhUFbMFHylBuy7p")
    fun getRoverManifest(@Path("rover") rover : String) : Call<PhotoManifestResponse>

    companion object {
        operator fun invoke() : MarsAPI {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            return Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/mars-photos/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
                .create(MarsAPI::class.java)
        }
    }
}