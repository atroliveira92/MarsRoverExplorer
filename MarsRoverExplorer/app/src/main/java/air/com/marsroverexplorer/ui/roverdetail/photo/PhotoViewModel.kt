package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import android.view.View
import androidx.lifecycle.ViewModel

class PhotoViewModel(var url: String, var onBackListener: OnMVVMBackPressed): ViewModel() {

    fun onClickBack(view: View) {
        onBackListener.onBack()
    }

    fun onClickDownload(view: View) {

    }

    fun onClickShare(view: View) {

    }
}