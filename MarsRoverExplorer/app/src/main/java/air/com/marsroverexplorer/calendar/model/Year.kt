package air.com.marsroverexplorer.calendar.model

data class Year(
    var year: Int
): HorizontalPicker {
    override fun getText(): String {
        return year.toString()
    }

    override fun getValue(): Int {
        return year
    }
}