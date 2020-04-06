package air.com.marsroverexplorer.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.media.MediaScannerConnection.MediaScannerConnectionClient
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class MyMediaConnection(var context: Context, var filePath: String, var mimeType: String?): MediaScannerConnectionClient {
    private val mediaScannerConnection = MediaScannerConnection(context, this)

    override fun onMediaScannerConnected() {
        mediaScannerConnection.scanFile(filePath, if (mimeType.isNullOrEmpty()) "" else mimeType)
    }

    override fun onScanCompleted(path: String?, uri: Uri?) {
        mediaScannerConnection.disconnect();
        val intent =  Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = FileProvider.getUriForFile(context,
            "air.com.marsroverexplorer.fileprovider", File(filePath))
        context.sendBroadcast(intent)
    }

    fun scan() {
//        if( android.os.Build.VERSION.SDK_INT >= 29 ) {
//            val values = ContentValues()
//            values.put( MediaStore.MediaColumns.RELATIVE_PATH, filePath )
//            values.put( MediaStore.MediaColumns.DATE_TAKEN, System.currentTimeMillis() )
//            values.put( MediaStore.MediaColumns.IS_PENDING, true )
//            context.contentResolver.update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values, null, null)
//
//        } else {

            mediaScannerConnection.connect()
//        }
    }

}