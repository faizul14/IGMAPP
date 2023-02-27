package com.informasigempabumi.igmapp.core.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class FormatTImeAgoTest {
    // Skenario: waktu di masa lalu lebih dari satu minggu yang lalu
    // Format waktu yang diharapkan: x minggu yang lalu
    @Test
    fun testGetTimeAgoWeeksAgo() {
        // Arrange
        val dateTimeStr = "2023-01-01T00:00:00Z" // 4 minggu yang lalu
        // Act
        val result = FormatTImeAgo.getTimeAgo(dateTimeStr)
        // Assert
        assertEquals("8 minggu yang lalu", result)
    }

}