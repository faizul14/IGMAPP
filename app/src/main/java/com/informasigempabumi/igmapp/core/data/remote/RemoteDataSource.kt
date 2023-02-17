package com.informasigempabumi.igmapp.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.informasigempabumi.igmapp.core.data.remote.network.ApiService
import com.informasigempabumi.igmapp.core.data.remote.response.GempaDiRasakanResponse
import com.informasigempabumi.igmapp.core.data.remote.response.GempaItem
import com.informasigempabumi.igmapp.core.data.remote.response.GempaTerbaruResponse
import com.informasigempabumi.igmapp.core.data.remote.response.GempaTerkiniResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {

    fun getGempaTerbaru(): LiveData<GempaItem> {
        val resultData = MutableLiveData<GempaItem>()

        //get data terbaru from API
        val client = apiService.getGempaTerbaru()
        client.enqueue(object : Callback<GempaTerbaruResponse> {
            override fun onResponse(
                call: Call<GempaTerbaruResponse>, response: Response<GempaTerbaruResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    resultData.value = response.body()!!.infogempa?.gempa!!
                } else {
                    Log.d(TAG, "MESSAGE: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GempaTerbaruResponse>, t: Throwable) {
                Log.d(TAG, "MESSAGE: ${t.message}")
            }

        })
        return resultData
    }

    fun getGempaTerkini(): LiveData<List<GempaItem>> {
        val resultData = MutableLiveData<List<GempaItem>>()

        val client = apiService.getGempaTerkini()
        client.enqueue(object : Callback<GempaTerkiniResponse> {
            override fun onResponse(
                call: Call<GempaTerkiniResponse>, response: Response<GempaTerkiniResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    resultData.value = response.body()!!.infogempa?.gempa as List<GempaItem>
                } else {
                    Log.d(TAG, "MESSAGE: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GempaTerkiniResponse>, t: Throwable) {
                Log.d(TAG, "MESSAGE: ${t.message}")
            }

        })
        return resultData
    }

    fun getGempaDiRasakan(): LiveData<List<GempaItem>> {
        val resultData = MutableLiveData<List<GempaItem>>()

        val client = apiService.getGempaDiRasakan()
        client.enqueue(object : Callback<GempaDiRasakanResponse> {
            override fun onResponse(
                call: Call<GempaDiRasakanResponse>, response: Response<GempaDiRasakanResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    resultData.value = response.body()!!.infogempa?.gempa as List<GempaItem>
                } else {
                    Log.d(TAG, "MESSAGE: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GempaDiRasakanResponse>, t: Throwable) {
                Log.d(TAG, "MESSAGE: ${t.message}")
            }

        })
        return resultData
    }

    companion object {
        const val TAG = "RemoteDataSource"

        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RemoteDataSource(service)
        }
    }

}