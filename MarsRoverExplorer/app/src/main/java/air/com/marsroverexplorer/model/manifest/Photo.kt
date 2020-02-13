package air.com.marsroverexplorer.model.manifest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo (
    /*@SerializedName("sol")
    @Expose*/
    var sol: Int,
//    @SerializedName("earth_date")
//    @Expose
    var earthDate: String,
//    @SerializedName("total_photos")
//    @Expose
    var totalPhotos: Int,
//    @SerializedName("cameras")
//    @Expose
    var cameras: List<String>
)
