package com.informasigempabumi.igmapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.informasigempabumi.igmapp.core.domain.model.DataGempa

interface UseCase {
    fun getGempaTerbaru() : LiveData<DataGempa>
    fun getGempaTerkini() : LiveData<List<DataGempa>>
    fun getGempaDiRasakan() : LiveData<List<DataGempa>>
    fun getDataCombine() : LiveData<List<DataGempa>>
}