package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.CameraRowBinding
import air.com.marsroverexplorer.ui.roverdetail.CameraPhotoAdapter.CameraPhotoViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class CameraPhotoAdapter(var cameraPhotos: List<CameraPhotoViewModel>): RecyclerView.Adapter<CameraPhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraPhotoViewHolder {
        return CameraPhotoViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.camera_row,
                            parent, false)
                )
    }

    override fun getItemCount(): Int = cameraPhotos.size

    override fun onBindViewHolder(holder: CameraPhotoViewHolder, position: Int) {
        holder.cameraRowBinding.viewModel = cameraPhotos[position]
    }

    inner class CameraPhotoViewHolder(var cameraRowBinding: CameraRowBinding): RecyclerView.ViewHolder(cameraRowBinding.root)
}