package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.PhotoViewBinding
import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

class PhotoActivity: AppCompatActivity(), OnMVVMBackPressed {

    companion object {
        private const val PHOTO_URL_ARG = "photo_arg"
        fun startActivity(context: Context, url: String) {
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putExtra(PHOTO_URL_ARG, url)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: PhotoViewBinding = DataBindingUtil.setContentView(this, R.layout.photo_view)

        val photo = intent.getStringExtra(PHOTO_URL_ARG)

        val factory = PhotoViewModelFactory(photo!!, this)
        val viewModel = ViewModelProviders.of(this, factory).get(PhotoViewModel::class.java)

        binding.viewModel = viewModel
    }

    override fun onBack() {
        onBackPressed()
    }
}