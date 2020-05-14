package air.com.marsroverexplorer.calendar.util

import air.com.marsroverexplorer.calendar.model.Month
import java.time.YearMonth
import java.util.*

class MonthUtil {
    companion object {
        val JANUARY = 1
        val FEBRUARY = 2
        val MARCH = 3
        val APRIL = 4
        val MAY = 5
        val JUNE = 6
        val JULY = 7
        val AUGUST = 8
        val SEPTEMBER = 9
        val OCTOBER = 10
        val NOVEMBER = 11
        val DECEMBER = 12

        fun loadMonths(): List<Month> {

            val months = mutableListOf<Month>()

            var month = Month(JANUARY, "Jan")
            months.add(month)

            month = Month(FEBRUARY, "Feb")
            months.add(month)

            month = Month(MARCH, "Mar")
            months.add(month)

            month = Month(APRIL, "Apr")
            months.add(month)

            month = Month(MAY, "May")
            months.add(month)

            month = Month(JUNE, "Jun")
            months.add(month)

            month = Month(JULY, "Jul")
            months.add(month)

            month = Month(AUGUST, "Aug")
            months.add(month)

            month = Month(SEPTEMBER, "Sep")
            months.add(month)

            month = Month(OCTOBER, "Oct")
            months.add(month)

            month = Month(NOVEMBER, "Nov")
            months.add(month)

            month = Month(DECEMBER, "Dec")
            months.add(month)

            return months
        }

        fun loadDaysOfMonth(year: Int, month: Int): List<Int> {
            val calendar = GregorianCalendar(year, month - 1, 1)
            val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            val array = mutableListOf<Int>()

            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            for (day in 1 until dayOfWeek) {
                array.add(0)
            }

            for (day in 1..daysOfMonth) {
                array.add(day)
            }

            return array
        }
    }

}