package air.com.marsroverexplorer.model.manifest

import android.os.Parcel
import android.os.Parcelable

data class PhotoManifest(
//    @SerializedName("name")
//    @Expose
    var name: String?,
//    @SerializedName("landing_date")
//    @Expose
    var landingDate: String?,
//    @SerializedName("launch_date")
//    @Expose
    var launchDate: String?,
//    @SerializedName("status")
//    @Expose
    var status: String?,
//    @SerializedName("max_sol")
//    @Expose
    var maxSol: Int,
//    @SerializedName("max_date")
//    @Expose
    var maxDate: String?,
    /*@SerializedName("total_photos")
    @Expose*/
    var totalPhotos: Int,
//    @SerializedName("photos")
//    @Expose
    var photos: List<Photo>

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createTypedArrayList(Photo)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(landingDate)
        parcel.writeString(launchDate)
        parcel.writeString(status)
        parcel.writeInt(maxSol)
        parcel.writeString(maxDate)
        parcel.writeInt(totalPhotos)
        parcel.writeTypedList(photos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoManifest> {
        override fun createFromParcel(parcel: Parcel): PhotoManifest {
            return PhotoManifest(parcel)
        }

        override fun newArray(size: Int): Array<PhotoManifest?> {
            return arrayOfNulls(size)
        }
    }
}