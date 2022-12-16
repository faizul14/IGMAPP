package com.informasigempabumi.igmapp.core.data.remote.network

import com.informasigempabumi.igmapp.core.data.remote.response.GempaDiRasakanResponse
import com.informasigempabumi.igmapp.core.data.remote.response.GempaTerbaruResponse
import com.informasigempabumi.igmapp.core.data.remote.response.GempaTerkiniResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("autogempa.json")
    fun getGempaTerbaru() : Call<GempaTerbaruResponse>

    @GET("gempaterkini.json")
    fun getGempaTerkini() : Call<GempaTerkiniResponse>

    @GET("gempadirasakan.json")
    fun getGempaDiRasakan() : Call<GempaDiRasakanResponse>
}