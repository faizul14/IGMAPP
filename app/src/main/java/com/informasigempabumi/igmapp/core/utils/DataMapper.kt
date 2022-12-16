package com.informasigempabumi.igmapp.core.utils

import com.informasigempabumi.igmapp.core.data.remote.response.GempaItem
import com.informasigempabumi.igmapp.core.domain.model.DataGempa

object DataMapper {
    fun DataResponseOneToModel(input: GempaItem): DataGempa {
        val data = DataGempa(
            dirasakan = input.dirasakan,
            wilayah = input.wilayah,
            shakemap = input.shakemap,
            kedalaman = input.kedalaman,
            jam = input.jam,
            coordinates = input.coordinates,
            potensi = input.potensi,
            tanggal = input.tanggal,
            bujur = input.bujur,
            magnitude = input.magnitude,
            lintang = input.lintang,
            dateTime = input.dateTime,
        )
        return data
    }

    fun DataResponseToModel(input: List<GempaItem>): List<DataGempa> {
        val dataList = ArrayList<DataGempa>()
        input.map {
            val data = DataGempa(
                dirasakan = it.dirasakan,
                wilayah = it.wilayah,
                shakemap = it.shakemap,
                kedalaman = it.kedalaman,
                jam = it.jam,
                coordinates = it.coordinates,
                potensi = it.potensi,
                tanggal = it.tanggal,
                bujur = it.bujur,
                magnitude = it.magnitude,
                lintang = it.lintang,
                dateTime = it.dateTime,
            )
            dataList.add(data)
        }
        return dataList
    }
}