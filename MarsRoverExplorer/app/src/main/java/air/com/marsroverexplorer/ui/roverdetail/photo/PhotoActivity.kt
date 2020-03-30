package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.PhotoViewBinding
import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import air.com.marsroverexplorer.ui.roverdetail.photo.PhotoViewModel.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.photo_view.*

class PhotoActivity: AppCompatActivity(), OnMVVMBackPressed, OnSharePhoto {

    companion object {
        private const val PHOTO_LIST_ARG = "photo_list_arg"
        private const val PHOTO_POSITION_ARG = "photo_position_arg"

        fun startActivity(context: Context, position: Int, photos: ArrayList<Photo>) {
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putParcelableArrayListExtra(PHOTO_LIST_ARG, photos)
            intent.putExtra(PHOTO_POSITION_ARG, position)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: PhotoViewBinding = DataBindingUtil.setContentView(this, R.layout.photo_view)

        val position = intent.getIntExtra(PHOTO_POSITION_ARG, 0)
        val photos = intent.getParcelableArrayListExtra<Photo>(PHOTO_LIST_ARG)

        val factory = PhotoViewModelFactory(position, photos!!, this, this)
        val viewModel = ViewModelProviders.of(this, factory).get(PhotoViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.photosLiveData.observe(this, Observer {
            viewPagerPhotos.adapter = PhotoPageAdapter(it)
            viewPagerPhotos.setCurrentItem(viewModel.position, true)
        })

        viewPagerPhotos.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                viewModel.position = position
            }

        })

    }

    override fun onBack() {
        onBackPressed()
    }

    override fun onErrorSharePhoto(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}