package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.RoverRowBinding
import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.ui.roverlist.RoverListAdapter.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter


class RoverListAdapter(private var rovers: List<Rover>, private var listener: OnClickRover) : Adapter<RoverListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverListViewHolder {

        return RoverListViewHolder(
                        DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context), R.layout.rover_row,
                                    parent, false)
                )
    }

    override fun getItemCount() = rovers.size

    override fun onBindViewHolder(holder: RoverListViewHolder, position: Int) {
        holder.roverRowBinding.rover = rovers[position]
        holder.roverRowBinding.root.setOnClickListener{
            listener.onClickRover(rovers[position].getName())
        }
    }

    inner class RoverListViewHolder(val roverRowBinding: RoverRowBinding) : RecyclerView.ViewHolder(roverRowBinding.root) {
//        var imgvRover : ImageView = itemView.findViewById(R.id.imgvRover)
//        var txvRoverTitle: TextView = itemView.findViewById(R.id.txvRoverTitle)
    }

    interface OnClickRover {
        fun onClickRover(rover: String)
    }
}