package air.com.marsroverexplorer.calendar.util

import air.com.marsroverexplorer.calendar.model.Year
import java.util.*

class YearUtil {

    companion object {

        fun loadYears(startYear: Int, endYear: Int) : List<Year>{
            var _startYear = startYear
            var _endYear = endYear

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            val currentYear = calendar.get(Calendar.YEAR)

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
    }
}