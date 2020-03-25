package air.com.marsroverexplorer.model.photo

import android.os.Parcel
import android.os.Parcelable

data class Photo(
    val id: String?,
    val sol: String?,
    val camera: Camera,
    var img_src: String?,
    val earth_date: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable<Camera>(Camera::class.java.classLoader) as Camera,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(sol)
        parcel.writeParcelable(camera, flags)
        parcel.writeString(img_src)
        parcel.writeString(earth_date)
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