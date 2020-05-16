package air.com.marsroverexplorer.calendar.view.daypicker

import air.com.marsroverexplorer.calendar.model.Day

interface OnDayPickerListener {
    fun onClickDay(day: Day)
}