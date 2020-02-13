package air.com.marsroverexplorer.model

data class Photo (
    val id: String,
    val sol: String,
    val camera: Camera,
    val img_src: String,
    val earth_date: String
)