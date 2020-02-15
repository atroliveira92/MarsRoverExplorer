package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.RoverListViewBinding
import air.com.marsroverexplorer.ui.roverlist.RoverListAdapter.OnClickRover
import air.com.marsroverexplorer.util.hide
import air.com.marsroverexplorer.util.show
import air.com.marsroverexplorer.util.snackbar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.rover_list_view.*

class RoverListActivity : AppCompatActivity(), OnClickRover, OnRoverListener {

    private lateinit var viewModel: RoverListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : RoverListViewBinding = DataBindingUtil.setContentView(this, R.layout.rover_list_view)
        viewModel = ViewModelProviders.of(this).get(RoverListViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.listener = this

        viewModel.loadRovers()

        viewModel.rovers.observe(this, Observer {rovers ->
            rvRovers.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = RoverListAdapter(rovers, this)
            }
        })
    }

    override fun onClickRover(rover: String) {
        viewModel.onClickRover(rover)
    }

    override fun onStartLoadManifest() {
        pbLoading.show()
    }

    override fun onFinishLoadManifest() {
        pbLoading.hide()
    }

    override fun onErrorLoadManifest(errorMessage: String) {
        pbLoading.hide()
        rootLayout.snackbar(errorMessage)
    }
}
