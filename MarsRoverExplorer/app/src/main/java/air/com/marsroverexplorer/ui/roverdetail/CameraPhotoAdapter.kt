package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.CameraRowBinding
import air.com.marsroverexplorer.ui.roverdetail.CameraPhotoAdapter.CameraPhotoViewHolder
import air.com.marsroverexplorer.ui.roverdetail.PhotoAdapter.OnClickPhoto
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CameraPhotoAdapter(var cameraPhotos: List<CameraPhotoViewModel>, var listener: OnClickPhoto): RecyclerView.Adapter<CameraPhotoViewHolder>() {

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

        val cameraPhoto = cameraPhotos[position]

        holder.cameraRowBinding.rvPhotos.also {
            it.layoutManager = GridLayoutManager(it.context, 4)
            it.hasFixedSize()
            it.adapter = PhotoAdapter(cameraPhoto, listener)
        }
    }

    inner class CameraPhotoViewHolder(var cameraRowBinding: CameraRowBinding): RecyclerView.ViewHolder(cameraRowBinding.root)
}