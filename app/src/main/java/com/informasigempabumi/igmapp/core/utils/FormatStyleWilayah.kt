package com.informasigempabumi.igmapp.core.utils

object FormatStyleWilayah {
    fun getLastWordInNewLine(sentence: String): String {
        val words = sentence.trim().split(" ")
        val lastWord = words.last()
        val sentenceWithoutLastWord = words.dropLast(1).joinToString(" ")
        return "$sentenceWithoutLastWord\n$lastWord"
    }

}