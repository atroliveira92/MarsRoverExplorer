package air.com.marsroverexplorer.ui.roverdetail.cameragallery

import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.ui.roverdetail.photo.PhotoActivity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraGalleryViewModel(var marsRoverName: String, var cameraName: String, var earthDate: String, var solDate:String, photos: List<Photo>): ViewModel() {

    private val mutableCameraPhotos = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> get() = mutableCameraPhotos

    init {
        mutableCameraPhotos.value = photos
    }

    fun onClickPhoto(url: String?, context: Context) {
        if (!url.isNullOrEmpty()) {
            PhotoActivity.startActivity(context, url)
        }
    }
}