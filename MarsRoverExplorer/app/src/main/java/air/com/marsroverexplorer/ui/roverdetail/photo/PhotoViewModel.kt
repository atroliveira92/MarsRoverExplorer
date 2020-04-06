package air.com.marsroverexplorer.ui.roverdetail.photo

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.model.photo.Photo
import air.com.marsroverexplorer.ui.listener.OnMVVMBackPressed
import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.*



class PhotoViewModel(var position: Int, var photos: List<Photo>, var onBackListener: OnMVVMBackPressed, var listener: OPhotoViewModelDelegate): ViewModel() {


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
        if (listener.hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            download(view.context)
        } else {
            listener.onRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Permission need to download Photo into gallery")
        }
    }

    fun download(context: Context) {
        val photo = photos[position]
        val filename = "filename_"+System.currentTimeMillis() +".jpg"

        Picasso.get().load(photo.img_src).into(object: Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                listener.onShowErrorMessage(context.getString(R.string.problem_to_share_photo))
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if (bitmap != null) {
                    try {
                        val fos: OutputStream?
                        fos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            val resolver = context.contentResolver
                            val contentValues = ContentValues()
                            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                            contentValues.put(
                                MediaStore.MediaColumns.RELATIVE_PATH,
                                Environment.DIRECTORY_PICTURES
                            )
                            val imageUri =
                                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                            resolver.openOutputStream(imageUri!!)
                        } else {
                            val imagesDir =
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                                    .toString()
                            val image = File(imagesDir, filename)
                            FileOutputStream(image)
                        }
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                        fos?.close()
                        listener.onDownloadFinished("Download Finished")
                    } catch (e: IOException) {
                        listener.onShowErrorMessage("Problem happened when tried to download. Please, try again")
                    }
                }
            }
        })
    }

    fun onClickShare(view: View) {
        val photo = photos[position]

        Picasso.get().load(photo.img_src).into(object: Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                listener.onShowErrorMessage(view.context.getString(R.string.problem_to_share_photo))
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
                        listener.onShowErrorMessage(view.context.getString(R.string.problem_to_share_photo))
                    }
                }
            }
        })
    }

    interface OPhotoViewModelDelegate {
        fun onShowErrorMessage(message: String)
        fun hasPermission(permission: String): Boolean
        fun onRequestPermission(permission: String, message: String)
        fun onDownloadFinished(message: String)
    }
}