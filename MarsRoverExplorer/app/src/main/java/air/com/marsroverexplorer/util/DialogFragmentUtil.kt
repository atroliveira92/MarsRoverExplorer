package air.com.marsroverexplorer.util

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class DialogFragmentUtil {

    companion object {

        /**
         * Handle show dialog Fragment. Check if there's already an fragment with the tag. If so, remove
         * the fragment with the tag from backstack and show it.
         * @param fm Fragment manager
         * @param tag Tag name of the fragment (don't pass null or empty)
         * @param dialogFragment dialog fragment to be show
         */
        public fun show(fm: FragmentManager, tag: String, dialogFragment: DialogFragment) {
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag(tag)

            if (prev != null) {
                ft.remove(prev)
            }

            ft.addToBackStack(null)
            dialogFragment.show(ft, tag)
        }
    }
}