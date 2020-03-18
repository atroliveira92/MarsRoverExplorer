package air.com.marsroverexplorer.ui.roverdetail

data class CameraPhotoViewModel (
    var cameraName: String,
    var imagesUrl: List<String> = ArrayList(),
    var remainingPhotoCount: Int = 0
)