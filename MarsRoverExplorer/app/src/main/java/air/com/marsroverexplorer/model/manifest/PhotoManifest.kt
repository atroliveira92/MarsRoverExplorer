package air.com.marsroverexplorer.model.manifest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoManifest (
//    @SerializedName("name")
//    @Expose
    var name : String,
//    @SerializedName("landing_date")
//    @Expose
    var landingDate :String,
//    @SerializedName("launch_date")
//    @Expose
    var launchDate: String,
//    @SerializedName("status")
//    @Expose
    var status: String,
//    @SerializedName("max_sol")
//    @Expose
    var maxSol: Int,
//    @SerializedName("max_date")
//    @Expose
    var maxDate: String,
    /*@SerializedName("total_photos")
    @Expose*/
    var totalPhotos: Int,
//    @SerializedName("photos")
//    @Expose
    var photos: List<Photo>

)