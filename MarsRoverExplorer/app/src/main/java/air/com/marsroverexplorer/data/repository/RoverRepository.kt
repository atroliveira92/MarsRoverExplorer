package air.com.marsroverexplorer.data.repository

import air.com.marsroverexplorer.data.network.MarsAPI
import air.com.marsroverexplorer.data.network.SafeApiRequest
import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.model.manifest.PhotoManifestResponse
import air.com.marsroverexplorer.model.photo.Photos
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

    suspend fun loadRoverPhotos(rover : String, earthDate: String) : Photos {
        return apiRequest{ api.listPhotosFromRover(rover, earthDate) }
    }

    suspend fun loadRoverManifest(rover: String) : PhotoManifestResponse {

        return apiRequest {  api.getRoverManifest(rover) }
    }
}