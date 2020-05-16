package air.com.marsroverexplorer.util

import air.com.marsroverexplorer.calendar.util.OnSnapPositionChangeListener
import air.com.marsroverexplorer.calendar.util.SnapOnScrollListener
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: SnapHelper,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
    onSnapPositionChangeListener: OnSnapPositionChangeListener
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
    addOnScrollListener(snapOnScrollListener)
}

fun RecyclerView.scrollToLinearSnapPosition(selectedPosition: Int, snapHelper: LinearSnapHelper) {
    scrollToPosition(selectedPosition)
    post {
        val view = layoutManager?.findViewByPosition(selectedPosition)
        view.let {
            val snapDistance = snapHelper.calculateDistanceToFinalSnap(layoutManager!!, view!!)
            if (snapDistance?.get(0) ?: 0 != 0 || snapDistance?.get(1) ?: 0 != 0) {
                scrollBy(snapDistance!![0], snapDistance[1])
            }
        }
    }
}