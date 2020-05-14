package air.com.marsroverexplorer.ui

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.calendar.util.*
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
    private lateinit var monthAdapter: HorizontalPickerAdapter
    private lateinit var yearAdapter: HorizontalPickerAdapter
    private lateinit var daysAdapter: DayPickerAdapter

    private var selectedMonth = 0
    private var selectedYear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.calendar_view)

        selectedMonth = 1
        selectedYear = 2012

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels
        val margin = 8 / resources.displayMetrics.density

        val cardWidth = (width - margin).div(3)

        //Month adapter setup
        mRecyclerViewMonth.setPadding(cardWidth.toInt(), 0, cardWidth.toInt(), 0)

        val snapHelperMonth = LinearSnapHelper()
        snapHelperMonth.attachToRecyclerView(mRecyclerViewMonth)

        mRecyclerViewMonth.setHasFixedSize(true)
        monthAdapter = HorizontalPickerAdapter(MonthUtil.loadMonths(), cardWidth.toInt())
        mRecyclerViewMonth.adapter = monthAdapter
        mRecyclerViewMonth.attachSnapHelperWithListener(snapHelperMonth, SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
            object : OnSnapPositionChangeListener {
                override fun onSnapPositionChange(position: Int) {
                    val value = monthAdapter.getValue(position)
                    mTextViewMonth.text = String.format("Month: %s", value.toString())
                    selectedMonth = value
                    val array = MonthUtil.loadDaysOfMonth(selectedYear, selectedMonth)
                    daysAdapter.update(array)
                }
            })


        //Year adapter setup
        mRecyclerViewYear.setPadding(cardWidth.toInt(), 0, cardWidth.toInt(), 0)

        val snapHelperYear = LinearSnapHelper()
        snapHelperYear.attachToRecyclerView(mRecyclerViewYear)

        mRecyclerViewYear.setHasFixedSize(true)
        yearAdapter = HorizontalPickerAdapter(YearUtil.loadYears(2012, 2020), cardWidth.toInt())
        mRecyclerViewYear.adapter = yearAdapter
        mRecyclerViewYear.attachSnapHelperWithListener(snapHelperYear, SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE, object : OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                val value = yearAdapter.getValue(position)
                mTextViewYear.text = String.format("Year: %s", value.toString())
                selectedYear = value
                val array = MonthUtil.loadDaysOfMonth(selectedYear, selectedMonth)
                daysAdapter.update(array)
            }
        })

        //Day adapter setup
        val array = MonthUtil.loadDaysOfMonth(selectedYear, selectedMonth)

        val size = displayMetrics.widthPixels / 7

        mRecyclerViewDays.setHasFixedSize(false)
        daysAdapter = DayPickerAdapter(array, size)
        mRecyclerViewDays.layoutManager = GridLayoutManager(this, 7)
        mRecyclerViewDays.adapter = daysAdapter


        //WeekAdapter setup
        mRecyclerViewWeek.setHasFixedSize(false)
        mRecyclerViewWeek.adapter = WeeksAdapter(WeekUtil.loadWeeks(), size)

    }
}