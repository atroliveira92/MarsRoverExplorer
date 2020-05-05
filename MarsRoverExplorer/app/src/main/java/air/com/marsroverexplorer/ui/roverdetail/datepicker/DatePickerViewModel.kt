package air.com.marsroverexplorer.ui.roverdetail.datepicker

import air.com.marsroverexplorer.R
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DatePickerViewModel(var currentDate: String?, var listener: OnDatePickerViewModelDelegate): ViewModel() {

    private var showDatePicker = MutableLiveData<Boolean>()
    val showDatePickerLiveData: LiveData<Boolean> get() = showDatePicker

    private var date = MutableLiveData<Calendar>()
    val dateLiveData: LiveData<Calendar> get() = date

    private var selectedDate:String? = null

    init {
        showDatePicker.value = true
        date.value = Calendar.getInstance()
        currentDate.let {
            val split = currentDate?.split("-")
            if (split?.size == 3) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, split[0].toInt())
                calendar.set(Calendar.MONTH, split[1].toInt() - 1)
                calendar.set(Calendar.DAY_OF_MONTH, split[2].toInt())

                date.value = calendar
            }

            selectedDate = currentDate
        }
    }

    fun close(view: View) {
        if (showDatePickerLiveData.value == true && selectedDate != null && !selectedDate.equals(currentDate)) {
            listener.closeWithEarthDate(selectedDate!!)
        } else {
            listener.close()
        }
    }

    fun changeDate(year: Int, month: Int, dayOfMonth: Int) {
        val m = month + 1
        selectedDate = "$year-$m-$dayOfMonth"
    }

    fun changePicker(view: View) {
        showDatePicker.value = when (view.id) {
            R.id.earth -> {
                true
            }
            R.id.mars -> {
                false
            }
            else -> {
                true
            }
        }
    }

}