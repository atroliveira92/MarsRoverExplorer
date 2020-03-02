package air.com.marsroverexplorer.model.manifest

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList


data class Photo(
    /*@SerializedName("sol")
    @Expose*/
    var sol: Int,
//    @SerializedName("earth_date")
//    @Expose
    var earthDate: String?,
//    @SerializedName("total_photos")
//    @Expose
    var totalPhotos: Int,
//    @SerializedName("cameras")
//    @Expose
    var cameras: ArrayList<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(sol)
        parcel.writeString(earthDate)
        parcel.writeInt(totalPhotos)
        parcel.writeStringList(cameras)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}
