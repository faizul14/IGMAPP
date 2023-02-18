package com.informasigempabumi.igmapp.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.OffsetDateTime
import java.time.ZoneOffset

object FormatTImeAgo {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeAgo(dateTimeStr: String): String {
        val now: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC)
        val dateTime: OffsetDateTime = OffsetDateTime.parse(dateTimeStr)
        val duration: Duration = Duration.between(dateTime, now)

        return when {
            duration.toDays() > 7 -> "${duration.toDays() / 7} minggu yang lalu"
            duration.toDays() > 0 -> "${duration.toDays()} hari yang lalu"
            duration.toHours() > 0 -> "${duration.toHours()} jam yang lalu"
            else -> "${duration.toMinutes()} menit yang lalu"
        }
    }
}