package air.com.marsroverexplorer.ui.roverdetail.cameragallery

import air.com.marsroverexplorer.model.photo.Photo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CameraGalleryViewModelFactory(val roverName: String, val cameraName: String, val earthDate: String, val solDate:String, val photos: List<Photo>): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CameraGalleryViewModel(roverName, cameraName, earthDate, solDate, photos) as T
    }
}