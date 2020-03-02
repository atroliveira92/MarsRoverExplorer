package air.com.marsroverexplorer.model.photo

import air.com.marsroverexplorer.model.photo.Camera

data class Photo (
    val id: String,
    val sol: String,
    val camera: Camera,
    val img_src: String,
    val earth_date: String
)