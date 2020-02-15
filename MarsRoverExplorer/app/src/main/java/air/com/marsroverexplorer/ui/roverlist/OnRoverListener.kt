package air.com.marsroverexplorer.ui.roverlist

interface OnRoverListener {

    fun onStartLoadManifest()
    fun onFinishLoadManifest()
    fun onErrorLoadManifest(errorMessage : String)
}
