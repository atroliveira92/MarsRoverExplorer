package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.RoverListViewBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.ui.roverlist.RoverListAdapter.OnClickRover
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rover_list_view.*

class RoverListActivity : AppCompatActivity(), RoverListListener, OnClickRover {

    private lateinit var adapter: RoverListAdapter
    private lateinit var viewModel: RoverListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : RoverListViewBinding = DataBindingUtil.setContentView(this, R.layout.rover_list_view)
        viewModel = ViewModelProviders.of(this).get(RoverListViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.listener = this

        adapter = RoverListAdapter(this)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.VERTICAL

        val recyclerView = rvRovers

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        viewModel.loadRovers()
    }

    override fun onLoadRovers(rovers: ArrayList<Rover>) {
        adapter.setRovers(rovers)
    }

    override fun onClickRover(rover: String) {
        viewModel.onClickRover(rover)
    }
}
