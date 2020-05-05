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

    var earthDate = MutableLiveData<String>()
    var solDate = MutableLiveData<String>()

    var listener: RoverDetailListener? = null

    var map: Map<String, List<Photo>>? = null

    private val mutableCameraPhotos = MutableLiveData<List<CameraPhotoViewModel>>()
    val listCameraPhotos : LiveData<List<CameraPhotoViewModel>> get() = mutableCameraPhotos

    fun init(photoManifest: PhotoManifest) {
        this.photoManifest = photoManifest
        this.solDate.value = photoManifest.maxSol.toString()

        if (photoManifest.maxDate != null) {
            loadPhotoByEarthDate(photoManifest.maxDate!!)
        } else {
            loadPhotoBySolDate(photoManifest.maxSol.toString())
        }
    }

    fun openDateFilterClick(view: View) {
        listener?.openDatePicker(earthDate.value!!)
    }

    fun onEarthDateSelected(earthDate: String) {
        loadPhotoByEarthDate(earthDate)
    }

    fun onSolDateSelected(solDate: String) {
        loadPhotoBySolDate(solDate)
    }

    private fun loadPhotoByEarthDate(earthDate: String) {
        this.earthDate.value = earthDate
        listener?.onShowLoading()

        mutableCameraPhotos.value = mutableListOf()

        Coroutines.main {
            try {
                val response = repository.loadRoverPhotosByEarthDate(photoManifest?.name!!, earthDate)
                buildCameraPhotosList(response.photos)
                if (response.photos.size > 0) {
                    this.solDate.value = response.photos[0].sol
                }

            } catch (e: ApiException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            } catch (e: NoInternetException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            } finally {
                listener?.onDismissLoading()
            }
        }
    }

    private fun loadPhotoBySolDate(solDate: String) {
        this.solDate.value = solDate

        listener?.onShowLoading()
        mutableCameraPhotos.value = mutableListOf()

        Coroutines.main {
            try {
                val response = repository.loadRoverPhotosBySolDate(photoManifest?.name!!, solDate)
                if (response.photos.size > 0) {
                    this.earthDate.value = response.photos[0].earth_date
                }

                buildCameraPhotosList(response.photos)

            } catch (e: ApiException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            } catch (e: NoInternetException) {
                e.printStackTrace()
                listener?.onError(e.message!!)
            } finally {
                listener?.onDismissLoading()
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
            CameraGalleryActivity.startActivity(context, roverName, cameraPhotoViewModel.cameraName, earthDate.value!!, solDate.value!!, photos as ArrayList<Photo>)
        }
    }
}