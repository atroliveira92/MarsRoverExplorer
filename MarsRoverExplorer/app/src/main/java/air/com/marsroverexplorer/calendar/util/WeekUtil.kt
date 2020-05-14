package air.com.marsroverexplorer.calendar.util

import air.com.marsroverexplorer.calendar.model.Week

class WeekUtil {

    companion object {
        val SUNDAY = 1
        val MONDAY = 2
        val TUESDAY = 3
        val WEDNESDAY = 4
        val THURSDAY = 5
        val FRIDAY = 6
        val SATURDAY = 7

        fun loadWeeks(): List<Week> {
            val weeks = mutableListOf<Week>()

            var week = Week(SUNDAY, "Sun")
            weeks.add(week)

            week = Week(MONDAY, "Mon")
            weeks.add(week)

            week = Week(TUESDAY, "Tue")
            weeks.add(week)

            week = Week(WEDNESDAY, "Wed")
            weeks.add(week)

            week = Week(THURSDAY, "Thu")
            weeks.add(week)

            week = Week(FRIDAY, "Fri")
            weeks.add(week)

            week = Week(SATURDAY, "Sat")
            weeks.add(week)

            return weeks
        }
    }
}