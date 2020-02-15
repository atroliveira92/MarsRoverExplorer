package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.data.repository.RoverRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class RoverListViewModelFactory (private val repository: RoverRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoverListViewModel(repository) as T
    }

}