package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotoViewModel(var position: Int, var photos: List<Photo>, var onBackListener: OnMVVMBackPressed): ViewModel() {

    private val mutableCameraPhotos = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> get() = mutableCameraPhotos

    init {
        mutableCameraPhotos.value = photos
        if (position < 0 || position > photos.size) {
            position = 0
        }
    }

    fun onClickBack(view: View) {
        onBackListener.onBack()
    }

    fun onClickDownload(view: View) {

    }

    fun onClickShare(view: View) {

    }
}