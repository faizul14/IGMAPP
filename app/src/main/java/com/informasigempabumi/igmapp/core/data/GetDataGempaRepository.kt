package com.informasigempabumi.igmapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.informasigempabumi.igmapp.core.data.remote.RemoteDataSource
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.repository.IGetDataGempaRespository
import com.informasigempabumi.igmapp.core.utils.DataMapper

class GetDataGempaRepository(private val remoteDataSource: RemoteDataSource) :
    IGetDataGempaRespository {
    override fun getGempaTerbaru(): LiveData<DataGempa> {
        return Transformations.map(remoteDataSource.getGempaTerbaru()) {
            DataMapper.DataResponseOneToModel(it)
        }
    }

    override fun getGempaTerkini(): LiveData<List<DataGempa>> {
        return Transformations.map(remoteDataSource.getGempaTerkini()) {
            DataMapper.DataResponseToModel(it)
        }
    }

    override fun getGempaDiRasakan(): LiveData<List<DataGempa>> {
        return Transformations.map(remoteDataSource.getGempaDiRasakan()) {
            DataMapper.DataResponseToModel(it)
        }
    }

    override fun getDataCombine(): LiveData<List<DataGempa>> {
        return Transformations.map(remoteDataSource.getDataCombine()) {
            DataMapper.DataResponseToModel(it)
        }
    }

}