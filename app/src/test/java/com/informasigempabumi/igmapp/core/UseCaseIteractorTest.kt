package com.informasigempabumi.igmapp.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.repository.IGetDataGempaRespository
import com.informasigempabumi.igmapp.core.domain.usecase.UseCaseIteractor
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
class UseCaseIteractorTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: IGetDataGempaRespository
    private lateinit var useCaseIter: UseCaseIteractor
    private val dataDummy = DataDummy.getGenerateDataDummy()
    private val dataDummyList = DataDummy.getListGenerateDataDummy()

    @Before
    fun setUp() {
        useCaseIter = UseCaseIteractor(repository)
    }

    @Test
    fun `when get useCaseGetDataGempaTerbaru Should Not Null`() {
        val observer = Observer<DataGempa> {}
        try {
            val ecpectedDataGempa = MutableLiveData<DataGempa>()
            ecpectedDataGempa.value = dataDummy
            Mockito.`when`(repository.getGempaTerbaru()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = useCaseIter.getGempaTerbaru().observeForever(observer)

            Mockito.verify(repository).getGempaTerbaru()
            assertNotNull(actualDataGempa)
        } finally {
            useCaseIter.getGempaTerbaru().removeObserver(observer)
        }
    }

    @Test
    fun `when get useCaseGetDataGempaTerkini Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<DataGempa>>()
            ecpectedDataGempa.value = dataDummyList
            Mockito.`when`(repository.getGempaTerkini()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = useCaseIter.getGempaTerkini().observeForever(observer)

            Mockito.verify(repository).getGempaTerkini()
            assertNotNull(actualDataGempa)
        } finally {
            useCaseIter.getGempaTerkini().removeObserver(observer)
        }
    }

    @Test
    fun `when get useCaseGetDataGempaDiRasakan Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<DataGempa>>()
            ecpectedDataGempa.value = dataDummyList
            Mockito.`when`(repository.getGempaDiRasakan()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = useCaseIter.getGempaDiRasakan().observeForever(observer)

            Mockito.verify(repository).getGempaDiRasakan()
            assertNotNull(actualDataGempa)
        } finally {
            useCaseIter.getGempaDiRasakan().removeObserver(observer)
        }
    }

    @Test
    fun `when get useCaseGetDataGempaCombine Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<DataGempa>>()
            ecpectedDataGempa.value = dataDummyList
            Mockito.`when`(repository.getDataCombine()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = useCaseIter.getDataCombine().observeForever(observer)

            Mockito.verify(repository).getDataCombine()
            assertNotNull(actualDataGempa)
        } finally {
            useCaseIter.getDataCombine().removeObserver(observer)
        }
    }


}