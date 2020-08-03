package ru.cnv.paxfultestapp.ui

import android.content.Context
import android.content.Intent
import ru.cnv.paxfultestapp.ui.activity.MainActivity
import java.time.temporal.TemporalAdjuster


object UiUtils {

    fun share(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)

        val manager = context.packageManager
        val info = manager.queryIntentActivities(intent, 0)
        if (info.isNotEmpty()) {
            context.startActivity(intent)
        }
    }
}