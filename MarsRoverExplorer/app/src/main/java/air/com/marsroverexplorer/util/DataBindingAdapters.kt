package air.com.marsroverexplorer.util

import android.widget.DatePicker
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.util.*

@BindingAdapter("imageResource")
fun loadDrawableImage(view: ImageView, drawableInt: Int) {
    view.setImageResource(drawableInt)
}

@BindingAdapter("loadThumb")
fun loadThumb(view: ImageView, url: String?) {
    Picasso.get().load(url)
        .resize(128, 128)
        .centerCrop()
        .into(view)
}

@BindingAdapter("loadPhoto")
fun loadPhoto(view: ImageView, url: String) {
    Picasso.get().load(url)
        .into(view)
}

@BindingAdapter("loadDate")
fun loadDate(view: DatePicker, date: String) {
    if (date.contains("-")) {
        val split = date.split("-")
        if (split.size == 3) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, split[0].toInt())
            calendar.set(Calendar.MONTH, split[1].toInt())
            calendar.set(Calendar.DAY_OF_MONTH, split[2].toInt())

            view.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        }
    }
}