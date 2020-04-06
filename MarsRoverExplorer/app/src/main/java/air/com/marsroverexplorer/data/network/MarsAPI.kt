package air.com.marsroverexplorer.data.network

import air.com.marsroverexplorer.model.manifest.PhotoManifestResponse
import air.com.marsroverexplorer.model.photo.Photos
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarsAPI {


    @GET("rovers/{rover}/photos?api_key=pkzzx9DLUejnkWlxoydv4aexzJhUFbMFHylBuy7p")
    suspend fun listPhotosFromRoverByEarthDate(@Path("rover") rover : String, @Query("earth_date") earthDate: String) : Response<Photos>

    @GET("rovers/{rover}/photos?api_key=pkzzx9DLUejnkWlxoydv4aexzJhUFbMFHylBuy7p")
    suspend fun listPhotosFromRoverBySolDate(@Path("rover") rover : String, @Query("sol") sol: String) : Response<Photos>

    @GET("manifests/{rover}?&api_key=pkzzx9DLUejnkWlxoydv4aexzJhUFbMFHylBuy7p")
    suspend fun getRoverManifest(@Path("rover") rover : String) : Response<PhotoManifestResponse>

    companion object {
        operator fun invoke(netWorkConnectionInterceptor: NetWorkConnectionInterceptor) : MarsAPI {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
                      .addInterceptor(netWorkConnectionInterceptor)

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