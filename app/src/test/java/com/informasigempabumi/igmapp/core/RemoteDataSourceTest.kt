package com.informasigempabumi.igmapp.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.informasigempabumi.igmapp.core.data.remote.RemoteDataSource
import com.informasigempabumi.igmapp.core.data.remote.network.ApiService
import com.informasigempabumi.igmapp.ui.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

//@RunWith(MockitoJUnitRunner::class)
//class RemoteDataSourceTest {
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var apiService: ApiService
//    private lateinit var remoteDataSource: RemoteDataSource
//    private val dataDummy = DataDummy.getGenerateDataDummyLayerData()
//    private val dataDummyList = DataDummy.getListGenerateDataDummyLayerData()
//
//    @Before
//    fun setUp() {
//        remoteDataSource = RemoteDataSource(apiService)
//    }
//}