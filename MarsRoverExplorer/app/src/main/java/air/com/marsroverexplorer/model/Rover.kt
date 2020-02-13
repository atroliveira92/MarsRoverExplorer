package air.com.marsroverexplorer.model

import air.com.marsroverexplorer.R

enum class Rover(private var id: Int, private var roverName: String, private var image: Int) {

    CURIOSITY (1, "Curiosity", R.drawable.curiosity),
    OPPORTUNITY (2, "Opportunity", R.drawable.opportunity),
    SPIRIT (3, "Spirit", R.drawable.spirit_rover);

    fun getId() : Int {
        return id
    }

    fun getName() : String{
        return roverName
    }

    fun getImageResource() : Int {
        return image;
    }

    companion object {
        fun getFromId(id: Int): Rover {
            return when (id) {
                1 -> {
                    CURIOSITY
                }
                2 -> {
                    OPPORTUNITY
                }
                3 -> {
                    SPIRIT
                }
                else -> CURIOSITY
            }

        }
    }
}