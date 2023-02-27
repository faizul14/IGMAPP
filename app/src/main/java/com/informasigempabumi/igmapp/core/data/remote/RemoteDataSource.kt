package com.informasigempabumi.igmapp.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.informasigempabumi.igmapp.core.data.remote.network.ApiService
import com.informasigempabumi.igmapp.core.data.remote.response.GempaDiRasakanResponse
import com.informasigempabumi.igmapp.core.data.remote.response.GempaItem
import com.informasigempabumi.igmapp.core.data.remote.response.GempaTerbaruResponse
import com.informasigempabumi.igmapp.core.data.remote.response.GempaTerkiniResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource(private val apiService: ApiService) {

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


    fun getDataCombine(): LiveData<List<GempaItem>> = liveData {
        val listData = mutableListOf<GempaItem>()

        // Mengambil data dari API GempaTerkini
        val gempaTerkiniResponse = withContext(Dispatchers.IO) {
            apiService.getGempaTerkini().execute()
        }

        if (gempaTerkiniResponse.isSuccessful) {
            gempaTerkiniResponse.body()?.let { response ->
                response.infogempa?.gempa?.let { listData.addAll(it.filterNotNull()) }
            }
        } else {
            Log.e(
                TAG,
                "Failed to get data from GempaTerkini API. Message: ${gempaTerkiniResponse.message()}"
            )
        }

        // Mengambil data dari API GempaDiRasakan
        val gempaDiRasakanResponse = withContext(Dispatchers.IO) {
            apiService.getGempaDiRasakan().execute()
        }

        if (gempaDiRasakanResponse.isSuccessful) {
            gempaDiRasakanResponse.body()?.let { response ->
                response.infogempa?.gempa?.let { listData.addAll(it.filterNotNull()) }
            }
        } else {
            Log.e(
                TAG,
                "Failed to get data from GempaDiRasakan API. Message: ${gempaDiRasakanResponse.message()}"
            )
        }

        emit(listData)
    }


    companion object {
        const val TAG = "RemoteDataSource"
    }

}