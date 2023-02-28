package com.informasigempabumi.igmapp.core.utils

import com.mapbox.mapboxsdk.geometry.LatLng
import org.junit.Assert.assertEquals
import org.junit.Test

class ParsingDataCoordinateToLatLongTest {

    /*
    1. Skenario ketika input berupa string koordinat yang valid,
    fungsi ParsingDataCoordinateToLatLong.parsing harus mengembalikan objek LatLng yang sesuai dengan input.

    2. Skenario ketika input berupa string yang tidak valid,
    fungsi ParsingDataCoordinateToLatLong.parsing harus melempar exception NumberFormatException.
    * */
    @Test
    fun `test parsing valid coordinate string`() {
        // Arrange
        val coordinateString = "37.7749,-122.4194"
        val expectedLatLng = LatLng(37.7749, -122.4194)

        // Act
        val result = ParsingDataCoordinateToLatLong.parsing(coordinateString)

        // Assert
        assertEquals(expectedLatLng, result)
    }

    @Test(expected = NumberFormatException::class)
    fun `test parsing invalid coordinate string`() {
        // Arrange
        val coordinateString = "invalidCoordinateString"

        // Act
        ParsingDataCoordinateToLatLong.parsing(coordinateString)

        // Assert
        // Expects NumberFormatException to be thrown
    }
}