package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.model.photo.Photo
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso

class PhotoPageAdapter(var photos: List<Photo>): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return photos.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = PhotoView(container.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        container.addView(imageView)

        val photo = photos[position]

        Picasso.get().load(photo.img_src).into(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as PhotoView)
    }
}