package air.com.marsroverexplorer.calendar.model

import java.util.*

data class Year(
    var year: Int
): HorizontalPicker {
    override fun getText(): String {
        return year.toString()
    }

    override fun getValue(): Int {
        return year
    }

    companion object {

        fun loadYears(startYear: Int, endYear: Int) : List<Year>{
            var _startYear = startYear
            var _endYear = endYear

            val currentYear = getCurrentYear()

            if (_endYear > currentYear) {
                _endYear = currentYear
            }

            val minYear = 1970

            if (_startYear < minYear) {
                _startYear = minYear
            }

            val years = mutableListOf<Year>()

            for(year in _startYear.._endYear) {
                years.add(Year(year))
            }

            return years
        }

        fun getCurrentYear(): Int {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            return calendar.get(Calendar.YEAR)
        }

        fun getMinYear(): Int {
            return 1970
        }
    }
}