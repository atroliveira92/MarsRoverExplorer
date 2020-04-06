package air.com.marsroverexplorer.util

import android.app.AlertDialog
import android.content.Context

class AppMessage {

    fun showPermissionExplanation(context: Context, message: String, listener: OnPermissionExplanation) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Mars Rover need your permission")
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.yes) {dialog, which ->
            listener.onClickOk()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    interface OnPermissionExplanation {
        fun onClickOk()
    }
}