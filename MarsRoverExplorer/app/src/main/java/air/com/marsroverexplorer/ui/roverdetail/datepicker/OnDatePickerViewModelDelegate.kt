package air.com.marsroverexplorer.ui.roverdetail.datepicker

interface OnDatePickerViewModelDelegate {

    fun closeWithEarthDate(earthDate: String)
    fun closeWithSolDate(solDate: String)
    fun close()
}