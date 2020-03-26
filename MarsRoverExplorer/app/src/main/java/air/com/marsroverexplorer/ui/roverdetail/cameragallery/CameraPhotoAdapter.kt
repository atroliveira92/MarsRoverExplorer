package air.com.marsroverexplorer.ui.roverdetail.cameragallery

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.CameraPhotoRowBinding
import air.com.marsroverexplorer.model.photo.Photo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class CameraPhotoAdapter(val photos: List<Photo>, val listener: OnCameraPhotoAdapter): RecyclerView.Adapter<CameraPhotoAdapter.CameraPhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraPhotoViewHolder {
        return CameraPhotoViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.camera_photo_row,
                        parent, false)
                )
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: CameraPhotoViewHolder, position: Int) {
        holder.cameraPhotoBinding.photo = photos[position]

        holder.cameraPhotoBinding.imgvPhoto.setOnClickListener {
            listener.onClickOnPhotoPosition(position)
        }
    }

    inner class CameraPhotoViewHolder(val cameraPhotoBinding: CameraPhotoRowBinding): RecyclerView.ViewHolder(cameraPhotoBinding.root)

    interface OnCameraPhotoAdapter {
        fun onClickOnPhotoPosition(position: Int)
    }
}