package air.com.marsroverexplorer.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

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