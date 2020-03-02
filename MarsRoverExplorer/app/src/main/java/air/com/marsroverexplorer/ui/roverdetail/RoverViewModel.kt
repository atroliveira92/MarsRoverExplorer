package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.data.repository.RoverRepository
import air.com.marsroverexplorer.model.manifest.PhotoManifest
import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.util.ApiException
import air.com.marsroverexplorer.util.Coroutines
import air.com.marsroverexplorer.util.NoInternetException
import android.view.View
import androidx.lifecycle.ViewModel

class RoverViewModel(private val repository: RoverRepository) : ViewModel(){

    var photoManifest: PhotoManifest? = null
    var earthDate: String? = null
    var solDate: String? = null

    var listener : RoverDetailListener? = null

    lateinit var cameraPhotos: MutableList<CameraPhotoViewModel>

    fun onInit(photoManifest: PhotoManifest) {
        this.photoManifest = photoManifest
        this.solDate = photoManifest.maxSol.toString()

        loadPhotoByEarthDate(photoManifest.maxDate!!)
    }

    fun openDateFilterClick(view: View) {

    }

    private fun loadPhotoByEarthDate(earthDate: String) {
        this.earthDate = earthDate

        Coroutines.main {
            try {
                val response = repository.loadRoverPhotos(photoManifest?.name!!, earthDate)
                cameraPhotos = ArrayList()
                buildCameraPhotosList(response.photo)

            } catch (e: ApiException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            } catch (e: NoInternetException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            }
        }
    }

    private fun buildCameraPhotosList(photos: List<Photo>) {
        Coroutines.default{
            var camera: String? = null
            var photoList: MutableList<String> = ArrayList()

            for (photo in photos) {
                if (camera == null || camera == photo.camera.full_name) {
                    camera = photo.camera.full_name
                    photoList.add(photo.img_src)
                } else {
                    cameraPhotos.add(CameraPhotoViewModel(camera, photoList))
                    camera = photo.camera.full_name
                    photoList = ArrayList()
                    photoList.add(photo.img_src)
                }
            }
        }
    }
}