package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.repository.RoverRepository
import air.com.marsroverexplorer.ui.roverdetail.RoverDetailActivity
import android.view.View
import androidx.lifecycle.ViewModel

class RoverListViewModel : ViewModel() {

    var listener: RoverListListener? = null

    fun loadRovers() {
        val rovers = arrayListOf(Rover.CURIOSITY, Rover.OPPORTUNITY, Rover.SPIRIT)
        listener?.onLoadRovers(rovers)
    }

    fun onClickOnCuriosity(view : View) {
        RoverDetailActivity.startActivity(view.context, Rover.CURIOSITY)
    }

    fun onClickOnOpportunity(view : View) {
        RoverDetailActivity.startActivity(view.context, Rover.OPPORTUNITY)
    }

    fun onClickOnSpirit(view : View) {
        RoverDetailActivity.startActivity(view.context, Rover.SPIRIT)
    }

    fun onClickRover(rover: String) {
        var response = RoverRepository().loadRoverManifest(rover)
    }
}