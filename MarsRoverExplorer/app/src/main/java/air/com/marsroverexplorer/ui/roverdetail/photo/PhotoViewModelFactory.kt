package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PhotoViewModelFactory(var url: String, var onBackListener: OnMVVMBackPressed): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(url, onBackListener) as T
    }
}