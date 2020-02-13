package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.model.Rover

interface RoverListListener {

    fun onLoadRovers(rovers: ArrayList<Rover>)
}