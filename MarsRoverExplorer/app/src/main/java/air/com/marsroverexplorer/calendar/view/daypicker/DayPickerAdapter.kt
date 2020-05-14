package air.com.marsroverexplorer.calendar.view.daypicker

import air.com.marsroverexplorer.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.day_row_view.view.*

class DayPickerAdapter(var days: List<Int>, val height: Int): RecyclerView.Adapter<DayPickerAdapter.DayPickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayPickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_row_view, parent, false)
        view.minimumHeight = height
        return DayPickerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: DayPickerViewHolder, position: Int) {
        holder.bindViewHolder(days[position])
    }

    fun update(days: List<Int>) {
        this.days = days
        notifyDataSetChanged()
    }

    inner class DayPickerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(day: Int) {
            if (day == 0) {
                itemView.mTextViewDay.text = ""
            } else {
                itemView.mTextViewDay.text = day.toString()
            }
        }
    }
}