package air.com.marsroverexplorer.data.repository

import air.com.marsroverexplorer.data.network.MarsAPI
import air.com.marsroverexplorer.data.network.SafeApiRequest
import air.com.marsroverexplorer.model.manifest.PhotoManifestResponse
import air.com.marsroverexplorer.model.photo.Photos

class RoverRepository(private val api: MarsAPI) : SafeApiRequest() {

    val TAG = "REQUEST"

    suspend fun loadRoverPhotosByEarthDate(rover : String, earthDate: String): Photos {
        return apiRequest{ api.listPhotosFromRoverByEarthDate(rover, earthDate) }
    }

    suspend fun loadRoverPhotosBySolDate(rover: String, solDate: String): Photos {
        return apiRequest { api.listPhotosFromRoverBySolDate(rover, solDate) }
    }

    suspend fun loadRoverManifest(rover: String) : PhotoManifestResponse {

        return apiRequest {  api.getRoverManifest(rover) }
    }
}