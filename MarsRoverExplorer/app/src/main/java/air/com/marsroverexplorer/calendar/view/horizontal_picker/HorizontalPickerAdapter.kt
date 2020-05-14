package air.com.marsroverexplorer.calendar.view.horizontal_picker

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.calendar.model.HorizontalPicker
import air.com.marsroverexplorer.calendar.view.horizontal_picker.HorizontalPickerAdapter.HorizontalPickerViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import kotlinx.android.synthetic.main.horizontal_picker_view.view.*

class HorizontalPickerAdapter(var horizontalPicker: List<HorizontalPicker> = arrayListOf(), var cardWidth: Int) : Adapter<HorizontalPickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalPickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_picker_view, parent, false)

        return HorizontalPickerViewHolder(view, cardWidth)
    }

    override fun getItemCount(): Int {
        return horizontalPicker.size
    }

    fun getValue(position: Int): Int {
        return horizontalPicker[position].getValue()
    }

    inner class HorizontalPickerViewHolder(itemView: View, width: Int) : ViewHolder(itemView) {
        init {
            val layoutParam = itemView.mFrameLayoutMonth.layoutParams
            layoutParam.width = cardWidth
            itemView.mFrameLayoutMonth.layoutParams = layoutParam
        }
        fun bindView(month: HorizontalPicker) {
            itemView.mTextViewHorizontal.text = month.getText()
        }
    }

    override fun onBindViewHolder(holder: HorizontalPickerViewHolder, position: Int) {
        holder.bindView(horizontalPicker[position])
    }
}