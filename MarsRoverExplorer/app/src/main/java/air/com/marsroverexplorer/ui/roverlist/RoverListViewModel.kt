package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.repository.RoverRepository
import air.com.marsroverexplorer.ui.roverdetail.RoverDetailActivity
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoverListViewModel : ViewModel() {

    private val mutableRovers = MutableLiveData<List<Rover>>()
    val rovers : LiveData<List<Rover>> get() = mutableRovers

    fun loadRovers() {
        mutableRovers.value = arrayListOf(Rover.CURIOSITY, Rover.OPPORTUNITY, Rover.SPIRIT)
    }


    fun onClickRover(rover: String) {
        var response = RoverRepository().loadRoverManifest(rover)
    }
}