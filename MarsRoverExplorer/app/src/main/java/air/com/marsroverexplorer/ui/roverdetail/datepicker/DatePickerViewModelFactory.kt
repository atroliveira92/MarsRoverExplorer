package air.com.marsroverexplorer.ui.roverdetail.datepicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DatePickerViewModelFactory(var currentDate: String?, var listener: OnDatePickerViewModelDelegate): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DatePickerViewModel(currentDate, listener) as T
    }
}