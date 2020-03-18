package air.com.marsroverexplorer.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageResource")
fun loadDrawableImage(view: ImageView, drawableInt: Int) {
    view.setImageResource(drawableInt)
}
