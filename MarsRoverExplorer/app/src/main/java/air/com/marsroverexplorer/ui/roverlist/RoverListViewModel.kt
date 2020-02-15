package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.data.network.NetWorkConnectionInterceptor
import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.data.repository.RoverRepository
import air.com.marsroverexplorer.util.ApiException
import air.com.marsroverexplorer.util.Coroutines
import air.com.marsroverexplorer.util.NoInternetException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoverListViewModel(private val repository: RoverRepository) : ViewModel() {

    private val mutableRovers = MutableLiveData<List<Rover>>()
    val rovers : LiveData<List<Rover>> get() = mutableRovers

    var listener : OnRoverListener? = null

    fun loadRovers() {
        mutableRovers.value = arrayListOf(Rover.CURIOSITY, Rover.OPPORTUNITY, Rover.SPIRIT)
    }


    fun onClickRover(rover: String) {
        listener?.onStartLoadManifest()

        Coroutines.main {
            try {
                val response = repository.loadRoverManifest(rover)
                listener?.onFinishLoadManifest()

            } catch (e: ApiException) {
                listener?.onErrorLoadManifest(e.message!!)
            } catch (e: NoInternetException) {
                listener?.onErrorLoadManifest(e.message!!)
            }
        }
    }
}