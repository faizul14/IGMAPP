package com.informasigempabumi.igmapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataGempa(
    val dirasakan: String? = null,

    val wilayah: String? = null,

    val shakemap: String? = null,

    val kedalaman: String? = null,

    val jam: String? = null,

    val coordinates: String? = null,

    val potensi: String? = null,

    val tanggal: String? = null,

    val bujur: String? = null,

    val magnitude: String? = null,

    val lintang: String? = null,

    val dateTime: String? = null
): Parcelable
