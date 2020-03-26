package air.com.marsroverexplorer.ui.roverdetail.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PhotoViewModelFactory(var url: String): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(url) as T
    }
}