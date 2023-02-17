package com.informasigempabumi.igmapp.core.di

import com.informasigempabumi.igmapp.core.data.GetDataGempaRepository
import com.informasigempabumi.igmapp.core.data.remote.RemoteDataSource
import com.informasigempabumi.igmapp.core.data.remote.network.ApiConfig
import com.informasigempabumi.igmapp.core.domain.repository.IGetDataGempaRespository
import com.informasigempabumi.igmapp.core.domain.usecase.UseCase
import com.informasigempabumi.igmapp.core.domain.usecase.UseCaseIteractor

object Injection {
    private fun provideRepostitory(): IGetDataGempaRespository {
        val remote = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        return GetDataGempaRepository.getInstance(remote)
    }

    fun provideUseCase(): UseCase {
        val repository = provideRepostitory()
        return UseCaseIteractor(repository)
    }
}