package com.informasigempabumi.igmapp.core.utils

import com.informasigempabumi.igmapp.core.utils.FormatStyleWilayah.getLastWordInNewLine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class FormatStyleWilayahTest {


    @Test
    fun testGetLastWordInNewLineNull() {
        val sentence = ""
        val expected = "\n"
        assertEquals(expected, getLastWordInNewLine(sentence))
    }

    /*
    should return sentence with new line character when given an empty sentence: untuk menguji fungsi ketika diberikan sebuah string kosong,
    dengan harapan hasil yang dikembalikan adalah sebuah string yang terdiri dari sebuah newline (\n).

    should return sentence with last word in new line when given a sentence with only one word:
    untuk menguji fungsi ketika diberikan sebuah kalimat yang hanya terdiri dari satu kata,
    dengan harapan hasil yang dikembalikan adalah sebuah string yang terdiri dari sebuah newline (\n) diikuti dengan kata tersebut.
    * */

    @Test
    fun `should return sentence with new line character when given an empty sentence`() {
        // given
        val sentence = ""

        // when
        val result = getLastWordInNewLine(sentence)

        // then
        assertNotNull(result)
        assertEquals("\n", result)
    }

    @Test
    fun `should return sentence with last word in new line when given a sentence with only one word`() {
        // given
        val sentence = "Hello\n"

        // when
        val result = getLastWordInNewLine(sentence)

        // then
        assertNotNull(result)
        assertEquals("\nHello", result)
    }
}