package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class PhotoViewModel(var position: Int, var photos: List<Photo>, 
                     var onBackListener: OnMVVMBackPressed, var onShareListener: OnSharePhoto): ViewModel() {

    private val mutableCameraPhotos = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> get() = mutableCameraPhotos

    init {
        mutableCameraPhotos.value = photos
        if (position < 0 || position > photos.size) {
            position = 0
        }
    }

    fun onClickBack(view: View) {
        onBackListener.onBack()
    }

    fun onClickDownload(view: View) {
        val photo = photos[position]

        Picasso.get().load(photo.img_src).into(object: Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                onShareListener.onErrorSharePhoto(view.context.getString(R.string.problem_to_share_photo))
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if (bitmap != null) {
                    val share = Intent(Intent.ACTION_SEND)
                    share.type = "image/jpeg"
                    val bytes = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes)

                    val file = File(view.context.externalCacheDir?.absolutePath + File.separator + "temporary_file.jpg")
                    try {
                        file.createNewFile()
                        val fo = FileOutputStream(file)
                        fo.write(bytes.toByteArray())
                        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.absolutePath))
                        view.context.startActivity(Intent.createChooser(share, "Share image"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        onShareListener.onErrorSharePhoto(view.context.getString(R.string.problem_to_share_photo))
                    }
                }
            }
        })
    }

    fun onClickShare(view: View) {

    }
    
    interface OnSharePhoto {
        fun onErrorSharePhoto(message: String)
    }
}