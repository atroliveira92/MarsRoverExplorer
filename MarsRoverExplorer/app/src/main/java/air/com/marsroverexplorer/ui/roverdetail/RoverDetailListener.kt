package air.com.marsroverexplorer.ui.roverdetail

import androidx.lifecycle.LiveData

interface RoverDetailListener {

    fun onStartLoading()
    fun onSuccess(roverResponse: LiveData<String>)
    fun onError(error : String)
}