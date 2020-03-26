package air.com.marsroverexplorer.ui.roverdetail

import air.com.marsroverexplorer.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PhotoAdapter(var cameraPhotoViewModel: CameraPhotoViewModel, var listener: OnClickPhoto): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_row, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = cameraPhotoViewModel.imagesUrl.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val url = cameraPhotoViewModel.imagesUrl[position]

        Picasso.get().load(url)
            .resize(128, 128)
            .centerCrop()
            .into(holder.imageView)

        if (position == itemCount - 1 && cameraPhotoViewModel.remainingPhotoCount > 0) {
            holder.frlMorePhotos.visibility = View.VISIBLE
            holder.txvNumber.text = String.format("+%s", cameraPhotoViewModel.remainingPhotoCount)

            holder.itemView.setOnClickListener(View.OnClickListener {
                listener.onClickMorePhotos(cameraPhotoViewModel)
            })
        } else {
            holder.frlMorePhotos.visibility = View.GONE

            holder.itemView.setOnClickListener(View.OnClickListener {
                listener.onClickOnPhoto(position, cameraPhotoViewModel)
            })
        }

    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imgvPhoto)
        var frlMorePhotos: FrameLayout = itemView.findViewById(R.id.frlMorePhotos)
        var txvNumber: TextView = itemView.findViewById(R.id.txvNumber)
    }

    interface OnClickPhoto {
        fun onClickOnPhoto(position: Int, cameraPhotoViewModel: CameraPhotoViewModel)
        fun onClickMorePhotos(cameraPhotoViewModel: CameraPhotoViewModel)
    }
}