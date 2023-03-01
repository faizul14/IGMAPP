package com.informasigempabumi.igmapp.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.informasigempabumi.igmapp.core.data.GetDataGempaRepository
import com.informasigempabumi.igmapp.core.data.remote.RemoteDataSource
import com.informasigempabumi.igmapp.core.data.remote.response.GempaItem
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.ui.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDataGempaRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var respository: GetDataGempaRepository
    private val dataDummy = DataDummy.getGenerateDataDummyLayerData()
    private val dataDummyList = DataDummy.getListGenerateDataDummyLayerData()

    @Before
    fun setUp() {
        respository = GetDataGempaRepository(remoteDataSource)
    }

    @Test
    fun `when get repositoryGetDataGempaTerbaru Should Not Null`() {
        val observer = Observer<DataGempa> {}
        try {
            val ecpectedDataGempa = MutableLiveData<GempaItem>()
            ecpectedDataGempa.value = dataDummy
            Mockito.`when`(remoteDataSource.getGempaTerbaru()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = respository.getGempaTerbaru().observeForever(observer)

            Mockito.verify(remoteDataSource).getGempaTerbaru()
            assertNotNull(actualDataGempa)
        } finally {
            respository.getGempaTerbaru().removeObserver(observer)
        }
    }

    @Test
    fun `when get repositoryGetDataGempaTerkini Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<GempaItem>>()
            ecpectedDataGempa.value = dataDummyList
            Mockito.`when`(remoteDataSource.getGempaTerkini()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = respository.getGempaTerkini().observeForever(observer)

            Mockito.verify(remoteDataSource).getGempaTerkini()
            assertNotNull(actualDataGempa)
        } finally {
            respository.getGempaTerkini().removeObserver(observer)
        }
    }

    @Test
    fun `when get repositoryGetDataGempaDiRasakan Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<GempaItem>>()
            ecpectedDataGempa.value = dataDummyList
            Mockito.`when`(remoteDataSource.getGempaDiRasakan()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = respository.getGempaDiRasakan().observeForever(observer)

            Mockito.verify(remoteDataSource).getGempaDiRasakan()
            assertNotNull(actualDataGempa)
        } finally {
            respository.getGempaDiRasakan().removeObserver(observer)
        }
    }

    @Test
    fun `when get repositoryGetDataGempaCombine Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<GempaItem>>()
            ecpectedDataGempa.value = dataDummyList
            Mockito.`when`(remoteDataSource.getDataCombine()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = respository.getDataCombine().observeForever(observer)

            Mockito.verify(remoteDataSource).getDataCombine()
            assertNotNull(actualDataGempa)
        } finally {
            respository.getDataCombine().removeObserver(observer)
        }
    }


}