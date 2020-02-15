package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.model.Rover.*
import air.com.marsroverexplorer.data.repository.RoverRepository
import androidx.lifecycle.ViewModel

class RoverViewModel : ViewModel(){

    var response: String? = null

    var roverTitle : String ? = null

    var listener : RoverDetailListener? = null


    fun onLoadRoverDetail(rover : Rover)  {

        roverTitle = when (rover) {
            CURIOSITY -> "Curiosity"
            OPPORTUNITY -> "Opportunity"
            SPIRIT -> "Spirit"
        }

        listener?.onStartLoading()

        val roverResponse = RoverRepository().loadRoverPhotos(rover.getName())

    }
}