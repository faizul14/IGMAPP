package com.informasigempabumi.igmapp.core.utils

import android.content.Context
import com.informasigempabumi.igmapp.R
import com.informasigempabumi.igmapp.core.utils.Resultmagnitudeimpact.resultImpact
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class ResultmagnitudeimpactTest {

    //skenario :  mengembalikan nilai yang sesuai dengan rentang nilai magnitudo yang diberikan sebagai argumen
    @Test
    fun testResultImpact() {
        val context = mock(Context::class.java)
        `when`(context.getString(R.string.above_0_below_2)).thenReturn("Result 0-2")
        `when`(context.getString(R.string.above_2_below_29)).thenReturn("Result 2-2.9")
        `when`(context.getString(R.string.above_3_below_39)).thenReturn("Result 3-3.9")
        `when`(context.getString(R.string.above_4_below_49)).thenReturn("Result 4-4.9")
        `when`(context.getString(R.string.above_5_below_59)).thenReturn("Result 5-5.9")
        `when`(context.getString(R.string.above_6_below_69)).thenReturn("Result 6-6.9")
        `when`(context.getString(R.string.above_7_below_79)).thenReturn("Result 7-7.9")
        `when`(context.getString(R.string.above_8_below_89)).thenReturn("Result 8-8.9")
        `when`(context.getString(R.string.above_9_below_99)).thenReturn("Result 9-9.9")
        `when`(context.getString(R.string.above_10_below_19)).thenReturn("Result 10-10.9")
        `when`(context.getString(R.string.above_11_below_119)).thenReturn("Result 11-11.9")
        `when`(context.getString(R.string.above_12_below_129)).thenReturn("Result 12-12.9")
        `when`(context.getString(R.string.over_13)).thenReturn("Result over 13")

        assertEquals("Result 0-2", resultImpact(1.5, context))
        assertEquals("Result 2-2.9", resultImpact(2.5, context))
        assertEquals("Result 3-3.9", resultImpact(3.5, context))
        assertEquals("Result 4-4.9", resultImpact(4.5, context))
        assertEquals("Result 5-5.9", resultImpact(5.5, context))
        assertEquals("Result 6-6.9", resultImpact(6.5, context))
        assertEquals("Result 7-7.9", resultImpact(7.5, context))
        assertEquals("Result 8-8.9", resultImpact(8.5, context))
        assertEquals("Result 9-9.9", resultImpact(9.5, context))
        assertEquals("Result 10-10.9", resultImpact(10.5, context))
        assertEquals("Result 11-11.9", resultImpact(11.5, context))
        assertEquals("Result 12-12.9", resultImpact(12.5, context))
        assertEquals("Result over 13", resultImpact(13.5, context))
    }

    // Skenario dengan kembalian non-null
    @Test
    fun testResultImpactNonNull() {
        val context = mock(Context::class.java)
        `when`(context.getString(R.string.above_5_below_59)).thenReturn("Moderate")
        val result = resultImpact(5.5, context)
        assertEquals("Moderate", result)
    }

    // Skenario dengan kembalian null
    @Test
    fun testResultImpactNull() {
        val context = mock(Context::class.java)
        `when`(context.getString(R.string.above_5_below_59)).thenReturn(null)
        val result = resultImpact(5.5, context)
        assertNull(result)
    }
}