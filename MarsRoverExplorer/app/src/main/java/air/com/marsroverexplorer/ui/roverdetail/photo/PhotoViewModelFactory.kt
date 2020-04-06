package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import air.com.marsroverexplorer.ui.roverdetail.photo.PhotoViewModel.OPhotoViewModelDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PhotoViewModelFactory(var position: Int, var photos: List<Photo>,
                            var onBackListener: OnMVVMBackPressed, var oListenerViewModelDelegate: OPhotoViewModelDelegate): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(position, photos, onBackListener, oListenerViewModelDelegate) as T
    }
}