package air.com.marsroverexplorer.data.repository

import air.com.marsroverexplorer.data.network.MarsAPI
import air.com.marsroverexplorer.data.network.SafeApiRequest
import air.com.marsroverexplorer.model.Photo
import air.com.marsroverexplorer.model.manifest.PhotoManifestResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoverRepository(private val api: MarsAPI) : SafeApiRequest() {

    val TAG = "REQUEST"

    fun loadRoverPhotos(rover : String) : LiveData<List<Photo>> {

        val roverResponse = MutableLiveData<List<Photo>>()

        api.listPhotosFromRover(rover.toLowerCase()).enqueue(object: Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) =
                if (response.isSuccessful && response.body() != null) {

                    val json = JSONObject(response.body()!!.string())

                    val gson = GsonBuilder().create()
                    val photo= gson.fromJson(json["photos"].toString(),Array<Photo>::class.java).toList()

                    roverResponse.value = photo
                } else {
                    roverResponse.value = null
                }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Log.i(TAG, t.message)
                roverResponse.value = null
            }

        })

        return roverResponse
    }

    suspend fun loadRoverManifest(rover: String) : PhotoManifestResponse {

        return apiRequest {  api.getRoverManifest(rover) }
    }
}