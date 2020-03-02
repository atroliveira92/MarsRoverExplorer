package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.util.toast
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import air.com.marsroverexplorer.databinding.RoverDetailViewBinding
import air.com.marsroverexplorer.model.manifest.PhotoManifest
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RoverDetailActivity : AppCompatActivity(), KodeinAware, RoverDetailListener {

    override val kodein by kodein()
    private val factory: RoverViewModelFactory by instance()

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
        val viewModel = ViewModelProviders.of(this, factory).get(RoverViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.listener = this

        val bundle = intent.getBundleExtra(BUNDLE)
        val photoManifest = bundle?.getParcelable<PhotoManifest>(PHOTO_MANIFEST)

        viewModel.onInit(photoManifest!!)
    }

    override fun onStartLoading() {
        //pbRover.show()
    }

    override fun onError(error: String) {
        //pbRover.hide()
        toast(error)
    }

    override fun onSuccess(roverResponse: LiveData<String>) {
        roverResponse.observe(this, Observer {
            //pbRover.hide()
            //txvResponse.text = it
        })
    }
}