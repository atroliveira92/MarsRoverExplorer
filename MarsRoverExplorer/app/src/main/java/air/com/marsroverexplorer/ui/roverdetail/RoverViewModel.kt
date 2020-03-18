package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.data.repository.RoverRepository
import air.com.marsroverexplorer.model.manifest.PhotoManifest
import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.util.ApiException
import air.com.marsroverexplorer.util.Coroutines
import air.com.marsroverexplorer.util.NoInternetException

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Math.min

class RoverViewModel(private val repository: RoverRepository) : ViewModel(){

    var photoManifest: PhotoManifest? = null
    var earthDate: String? = null
    var solDate: String? = null

    var listener : RoverDetailListener? = null

    var map: Map<String, List<Photo>>? = null

    private val mutableCameraPhotos = MutableLiveData<List<CameraPhotoViewModel>>()
    val listCameraPhotos : LiveData<List<CameraPhotoViewModel>> get() = mutableCameraPhotos

    fun onInit(photoManifest: PhotoManifest) {
        this.photoManifest = photoManifest
        this.solDate = photoManifest.maxSol.toString()

        loadPhotoByEarthDate(photoManifest.photos[2].earthDate!!)
        //loadPhotoByEarthDate(photoManifest.maxDate!!)
    }

    private fun loadPhotoByEarthDate(earthDate: String) {
        this.earthDate = earthDate

        Coroutines.main {
            try {
                val response = repository.loadRoverPhotos(photoManifest?.name!!, earthDate)
                buildCameraPhotosList(response.photos)

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
        val cameraViewModels = ArrayList<CameraPhotoViewModel>()

        map = photos.groupBy{it.camera.full_name}
        map?.let {it ->
            it.forEach {
                val viewModel = CameraPhotoViewModel(it.key)
                val limit = min(8, it.value.size)
                viewModel.imagesUrl = it.value.subList(0, limit).map { x -> x.img_src }
                viewModel.remainingPhotoCount = it.value.size - limit

                cameraViewModels.add(viewModel)
            }
        }

        mutableCameraPhotos.value = cameraViewModels
    }
}