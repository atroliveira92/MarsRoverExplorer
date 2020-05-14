package air.com.marsroverexplorer.calendar.view.daypicker

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.calendar.model.Week
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.day_row_view.view.*

class WeeksAdapter(var weeks: List<Week>, var width: Int): RecyclerView.Adapter<WeeksAdapter.WeekViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_row_view, parent, false)
        view.layoutParams.width = width
        return WeekViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weeks.size
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bindView(weeks[position])
    }

    inner class WeekViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(week: Week) {
            itemView.mTextViewDay.text = week.text
        }
    }
}