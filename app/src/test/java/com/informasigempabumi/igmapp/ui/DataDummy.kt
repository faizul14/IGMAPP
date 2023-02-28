package com.informasigempabumi.igmapp.ui

import com.informasigempabumi.igmapp.core.domain.model.DataGempa

object DataDummy {
    fun getGenerateDataDummy(): DataGempa = DataGempa(
        dirasakan = "Terasa",
        wilayah = "Banda Aceh",
        shakemap = "Shakemap 1",
        kedalaman = "10 km",
        jam = "13:30",
        coordinates = "3.7892° N, 97.2399° E",
        potensi = "Tinggi",
        tanggal = "2022-02-28",
        bujur = "97.2399° E",
        magnitude = "6.0",
        lintang = "3.7892° N",
        dateTime = "2022-02-28 13:30:00"
    )

    fun getListGenerateDataDummy(): List<DataGempa> {
        val newListDtg = ArrayList<DataGempa>()
        for (i in 0..15){
            val data = DataGempa(
                dirasakan = "Terasa Data $i",
                wilayah = "Banda Aceh",
                shakemap = "Shakemap $i",
                kedalaman = "10 km",
                jam = "13:30",
                coordinates = "3.7892° N, 97.2399° E",
                potensi = "Tinggi",
                tanggal = "2022-02-28",
                bujur = "97.2399° E",
                magnitude = "6.0",
                lintang = "3.7892° N",
                dateTime = "2022-02-28 13:30:00"
            )
            newListDtg.add(data)
        }
        return newListDtg
    }
}