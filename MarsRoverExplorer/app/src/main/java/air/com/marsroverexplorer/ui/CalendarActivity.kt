package air.com.marsroverexplorer.ui

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.calendar.model.Date
import air.com.marsroverexplorer.calendar.model.Month
import air.com.marsroverexplorer.calendar.model.Week
import air.com.marsroverexplorer.calendar.model.Year
import air.com.marsroverexplorer.calendar.util.*
import air.com.marsroverexplorer.calendar.view.CalendarFragment
import air.com.marsroverexplorer.calendar.view.daypicker.DayPickerAdapter
import air.com.marsroverexplorer.calendar.view.daypicker.WeeksAdapter
import air.com.marsroverexplorer.calendar.view.horizontal_picker.HorizontalPickerAdapter
import air.com.marsroverexplorer.util.attachSnapHelperWithListener
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper

import kotlinx.android.synthetic.main.calendar_view.*

class CalendarActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.test_calendar)
        val selectDate = Date(2020, Month.JANUARY, 13)

        val disabledDate = ArrayList<Date>()
        disabledDate.add(Date(2012, Month.JANUARY, 3))
        disabledDate.add(Date(2012, Month.JANUARY, 12))
        disabledDate.add(Date(2012, Month.JANUARY, 20))

        supportFragmentManager
            .beginTransaction()
            .add(R.id.mFrameLayoutFragmentHolder, CalendarFragment.newInstance(2012, 2020, selectDate, null))
            .commit()
    }
}