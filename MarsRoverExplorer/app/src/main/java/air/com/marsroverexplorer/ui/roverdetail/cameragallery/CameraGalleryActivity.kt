package air.com.marsroverexplorer.ui.roverdetail.cameragallery

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.PhotoListViewBinding
import air.com.marsroverexplorer.model.photo.Photo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.photo_list_view.*

class CameraGalleryActivity: AppCompatActivity() {

    companion object {
        private const val CAMERA_NAME_PARAM = "camera_photo_param"
        private const val LIST_PHOTO_PARAM = "list_photo_param"
        private const val ROVER_NAME_PARAM = "rover_name_param"
        private const val EARTH_DATE_PARAM = "earth_date_param"
        private const val SOL_DATE_PARAM = "sol_date_param"

        fun startActivity(context: Context, roverName: String, cameraName: String, earthDate: String, solDate: String, photos: ArrayList<Photo>) {
            val intent = Intent(context, CameraGalleryActivity::class.java)
            intent.putExtra(CAMERA_NAME_PARAM, cameraName)
            intent.putExtra(ROVER_NAME_PARAM, roverName)
            intent.putExtra(EARTH_DATE_PARAM, earthDate)
            intent.putExtra(SOL_DATE_PARAM, solDate)
            intent.putParcelableArrayListExtra(LIST_PHOTO_PARAM, photos)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: PhotoListViewBinding = DataBindingUtil.setContentView(this, R.layout.photo_list_view)

        val roverName = intent.getStringExtra(ROVER_NAME_PARAM)
        val cameraName = intent.getStringExtra(CAMERA_NAME_PARAM)
        val earthDate = intent.getStringExtra(EARTH_DATE_PARAM)
        val solDate = intent.getStringExtra(SOL_DATE_PARAM)
        val photos = intent.getParcelableArrayListExtra<Photo>(LIST_PHOTO_PARAM)

        val factory = CameraGalleryViewModelFactory(roverName!!, cameraName!!, earthDate!!, solDate!!, photos!!)
        val viewModel = ViewModelProviders.of(this, factory).get(CameraGalleryViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.photosLiveData.observe(this, Observer {photosCamera ->
            rvCameraGallery.also {
                it.setHasFixedSize(true)
                it.layoutManager = GridLayoutManager(this, 4)
                it.adapter = CameraPhotoAdapter(photosCamera)
            }
        })

    }
}