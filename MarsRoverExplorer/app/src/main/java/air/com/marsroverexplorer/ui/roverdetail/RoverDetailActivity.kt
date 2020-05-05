package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.util.toast
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import air.com.marsroverexplorer.databinding.RoverDetailViewBinding
import air.com.marsroverexplorer.model.manifest.PhotoManifest
import air.com.marsroverexplorer.ui.roverdetail.PhotoAdapter.OnClickPhoto
import air.com.marsroverexplorer.ui.roverdetail.datepicker.DatePickerDialogFragment
import air.com.marsroverexplorer.util.DialogFragmentUtil
import air.com.marsroverexplorer.util.hide
import air.com.marsroverexplorer.util.show
import android.content.Context
import android.content.Intent

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.rover_detail_view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RoverDetailActivity : AppCompatActivity(), KodeinAware, RoverDetailListener, OnClickPhoto, DatePickerDialogFragment.OnDatePickeDialogFragment {

    override val kodein by kodein()
    private val factory: RoverViewModelFactory by instance()
    private lateinit var viewModel: RoverViewModel

    companion object {
        const val PHOTO_MANIFEST = "photo_manifest"
        const val BUNDLE = "BUNDLE"

        fun startActivity(context: Context, photoManifest: PhotoManifest) {
            val intent = Intent(context, RoverDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(PHOTO_MANIFEST, photoManifest)
            intent.putExtra(BUNDLE, bundle)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : RoverDetailViewBinding = DataBindingUtil.setContentView(this, R.layout.rover_detail_view)
        viewModel = ViewModelProviders.of(this, factory).get(RoverViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.listener = this

        val bundle = intent.getBundleExtra(BUNDLE)
        val photoManifest = bundle?.getParcelable<PhotoManifest>(PHOTO_MANIFEST)

        viewModel.init(photoManifest!!)

        viewModel.listCameraPhotos.observe(this, Observer { camera ->
            rvRoverGallery.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = CameraPhotoAdapter(camera, this)
            }
        })
    }


    override fun onError(error: String) {
        //pbRover.hide()
        toast(error)
    }

    override fun openDatePicker(currentDate: String) {
        DialogFragmentUtil.show(supportFragmentManager, "Calendar", DatePickerDialogFragment.newInstance(currentDate, this))
    }

    override fun onClickOnPhoto(position: Int, cameraPhotoViewModel: CameraPhotoViewModel) {
        viewModel.onClickPhoto(position, cameraPhotoViewModel, this)
    }

    override fun onClickMorePhotos(cameraPhotoViewModel: CameraPhotoViewModel) {
        viewModel.onClickOnMorePhotos(cameraPhotoViewModel, this)
    }

    override fun onEarthDateSelected(earthDate: String) {
        viewModel.onEarthDateSelected(earthDate)
    }

    override fun onSolDateSelected(solDate: String) {
        viewModel.onSolDateSelected(solDate)
    }

    override fun onShowLoading() {
        pgLoadPhotos.show()
    }

    override fun onDismissLoading() {
        pgLoadPhotos.hide()
    }
}