package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.util.toast
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import air.com.marsroverexplorer.databinding.RoverDetailViewBinding
import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.util.hide
import air.com.marsroverexplorer.util.show
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.rover_detail_view.*

class RoverDetailActivity : AppCompatActivity(), RoverDetailListener {

    companion object {
        val ROVER_PARM = "rover_param"
        fun startActivity(context: Context, rover: Rover) {
            val intent = Intent(context, RoverDetailActivity::class.java)
            intent.putExtra(ROVER_PARM, rover.getId())

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : RoverDetailViewBinding = DataBindingUtil.setContentView(this, R.layout.rover_detail_view)
        val viewModel = ViewModelProviders.of(this).get(RoverViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.listener = this

        val rover = intent.getIntExtra(ROVER_PARM, Rover.CURIOSITY.getId())

        viewModel.onLoadRoverDetail(Rover.getFromId(rover))

    }

    override fun onStartLoading() {
        pbRover.show()
    }

    override fun onError(error: String) {
        pbRover.hide()
        toast(error)
    }

    override fun onSuccess(roverResponse: LiveData<String>) {
        roverResponse.observe(this, Observer {
            pbRover.hide()
            txvResponse.text = it
        })
    }
}