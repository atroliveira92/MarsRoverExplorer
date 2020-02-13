package air.com.marsroverexplorer.ui.roverlist

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.model.Rover
import air.com.marsroverexplorer.ui.roverlist.RoverListAdapter.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter


class RoverListAdapter(var listener: OnClickRover) : Adapter<RoverListViewHolder>() {

    private var rovers = ArrayList<Rover>()

    fun setRovers(rovers : ArrayList<Rover>) {
        this.rovers = rovers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rover_row, parent, false)
        return RoverListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rovers.size
    }

    override fun onBindViewHolder(holder: RoverListViewHolder, position: Int) {
        val rover = rovers[position]
        holder.imgvRover.setImageDrawable(ContextCompat.getDrawable(holder.imgvRover.context, rover.getImageResource()))
        holder.txvRoverTitle.text = rover.getName()
        holder.imgvRover.setOnClickListener { listener.onClickRover(rover.getName()) }
    }

    class RoverListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgvRover : ImageView = itemView.findViewById(R.id.imgvRover)
        var txvRoverTitle: TextView = itemView.findViewById(R.id.txvRoverTitle)
    }

    interface OnClickRover {
        fun onClickRover(rover: String)
    }
}