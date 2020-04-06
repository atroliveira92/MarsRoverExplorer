package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.data.repository.RoverRepository
import air.com.marsroverexplorer.model.manifest.PhotoManifest
import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.ui.roverdetail.cameragallery.CameraGalleryActivity
import air.com.marsroverexplorer.ui.roverdetail.photo.PhotoActivity
import air.com.marsroverexplorer.util.ApiException
import air.com.marsroverexplorer.util.Coroutines
import air.com.marsroverexplorer.util.NoInternetException
import android.content.Context
import android.view.View

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

        if (photoManifest.photos[2].earthDate != null) {
            loadPhotoByEarthDate(photoManifest.photos[2].earthDate!!)
        } else {
            loadPhotoBySolDate(photoManifest.photos[2].sol.toString())
        }
        //loadPhotoByEarthDate(photoManifest.maxDate!!)
    }

    fun openDateFilterClick(view: View) {

    }

    private fun loadPhotoByEarthDate(earthDate: String) {
        this.earthDate = earthDate

        Coroutines.main {
            try {
                val response = repository.loadRoverPhotosByEarthDate(photoManifest?.name!!, earthDate)
                buildCameraPhotosList(response.photos)
                this.solDate = response.photos[0].sol

            } catch (e: ApiException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            } catch (e: NoInternetException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            }
        }
    }

    private fun loadPhotoBySolDate(solDate: String) {
        this.solDate = solDate

        Coroutines.main {
            try {
                val response = repository.loadRoverPhotosBySolDate(photoManifest?.name!!, solDate)
                this.earthDate = response.photos[0].earth_date

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
        Coroutines.default {
            val cameraViewModels = ArrayList<CameraPhotoViewModel>()
            map = photos.groupBy{ it.camera.full_name!! }
            map?.let {it ->
                it.forEach {
                    val viewModel = CameraPhotoViewModel(it.key)
                    val limit = min(8, it.value.size)
                    viewModel.imagesUrl = it.value.subList(0, limit).map { x -> x.img_src!! }
                    viewModel.remainingPhotoCount = it.value.size - limit

                    cameraViewModels.add(viewModel)
                }
            }

            bindMainThreadList(cameraViewModels)
        }
    }

    private fun bindMainThreadList(cameraViewModels: ArrayList<CameraPhotoViewModel>) {
        Coroutines.main {
            mutableCameraPhotos.value = cameraViewModels
        }
    }

    fun onClickPhoto(position: Int, cameraPhotoViewModel: CameraPhotoViewModel, context: Context) {
        val photos = this.map?.get(cameraPhotoViewModel.cameraName)
        PhotoActivity.startActivity(context, position, photos as ArrayList<Photo>)
    }

    fun onClickOnMorePhotos(cameraPhotoViewModel: CameraPhotoViewModel, context: Context) {
        val photos = this.map?.get(cameraPhotoViewModel.cameraName)
        var roverName = "Rover"
        if (photoManifest != null && !photoManifest!!.name.isNullOrEmpty()) {
            roverName = photoManifest?.name.toString()
        }

        if (photos != null) {
            CameraGalleryActivity.startActivity(context, roverName, cameraPhotoViewModel.cameraName, earthDate!!, solDate!!, photos as ArrayList<Photo>)
        }
    }
}