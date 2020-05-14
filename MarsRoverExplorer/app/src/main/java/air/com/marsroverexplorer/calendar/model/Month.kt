package air.com.marsroverexplorer.calendar.model

data class Month (
    var id: Int,
    var name: String) : HorizontalPicker {
    override fun getText(): String {
        return name
    }

    override fun getValue(): Int {
        return id
    }

}

