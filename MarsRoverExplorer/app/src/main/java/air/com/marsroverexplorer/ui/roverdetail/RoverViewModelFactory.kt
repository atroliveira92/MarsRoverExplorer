package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.data.repository.RoverRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class RoverViewModelFactory (private val repository: RoverRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoverViewModel(repository) as T
    }
}