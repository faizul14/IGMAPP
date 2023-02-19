package com.informasigempabumi.igmapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.repository.IGetDataGempaRespository

class UseCaseIteractor(private val repository: IGetDataGempaRespository): UseCase {
    override fun getGempaTerbaru(): LiveData<DataGempa> {
        return repository.getGempaTerbaru()
    }

    override fun getGempaTerkini(): LiveData<List<DataGempa>> {
        return repository.getGempaTerkini()
    }

    override fun getGempaDiRasakan(): LiveData<List<DataGempa>> {
        return repository.getGempaDiRasakan()
    }

    override fun getDataCombine(): LiveData<List<DataGempa>> {
        return repository.getDataCombine()
    }
}