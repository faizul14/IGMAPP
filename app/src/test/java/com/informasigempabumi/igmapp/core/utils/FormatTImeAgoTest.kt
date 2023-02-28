package com.informasigempabumi.igmapp.core.utils

import com.informasigempabumi.igmapp.core.utils.FormatTImeAgo.getTimeAgo
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset

class FormatTImeAgoTest {

    /*
    Skenario: waktu di masa lalu lebih dari bebrapa minggu yang lalu
    Format waktu yang diharapkan: x minggu yang lalu
    */
    @Test
    fun testGetTimeAgoWeeksAgo() {
        val now = OffsetDateTime.of(2023, 2, 28, 10, 0, 0, 0, ZoneOffset.UTC)
        val dateTimeStr = "2023-01-01T00:00:00Z"
        val expected = "8 minggu yang lalu"
        val result = getTimeAgo(dateTimeStr, now)
        assertEquals(expected, result)
    }

    /*
     Skenario: waktu di masa lalu lebih dari bebrapa hari yang lalu
     Format waktu yang diharapkan: x hari yang lalu
     */
    @Test
    fun testGetTimeAgoDayssAgo() {
        val now = OffsetDateTime.of(2023, 2, 28, 10, 0, 0, 0, ZoneOffset.UTC)
        val dateTimeStr = "2023-02-24T00:00:00Z"
        val expected = "4 hari yang lalu"
        val result = getTimeAgo(dateTimeStr, now)
        assertEquals(expected, result)
    }

    /*
    Skenario: waktu di masa lalu lebih dari bebrapa jam yang lalu
    Format waktu yang diharapkan: x jam yang lalu
    */
  @Test
    fun testGetTimeAgoHoursAgo() {
        val now = OffsetDateTime.of(2023, 2, 28, 10, 0, 0, 0, ZoneOffset.UTC)
        val dateTimeStr = "2023-02-28T00:00:00Z"
        val expected = "10 jam yang lalu"
        val result = getTimeAgo(dateTimeStr, now)
        assertEquals(expected, result)
    }

    /*
    Skenario: waktu di masa lalu lebih dari bebrapa menit yang lalu
    Format waktu yang diharapkan: x menit yang lalu
    */
     @Test
    fun testGetTimeAgoMinutesAgo() {
        val now = OffsetDateTime.of(2023, 2, 28, 10, 0, 0, 0, ZoneOffset.UTC)
        val dateTimeStr = "2023-02-28T09:46:00Z"
        val expected = "14 menit yang lalu"
        val result = getTimeAgo(dateTimeStr, now)
        assertEquals(expected, result)
    }
}