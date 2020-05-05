package air.com.marsroverexplorer.ui.roverdetail

interface RoverDetailListener {

    fun openDatePicker(currentDate: String)

    fun onError(error : String)

    fun onShowLoading()

    fun onDismissLoading()
}