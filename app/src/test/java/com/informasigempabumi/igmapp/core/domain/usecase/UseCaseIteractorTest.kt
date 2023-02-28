package com.informasigempabumi.igmapp.core.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.informasigempabumi.igmapp.core.data.GetDataGempaRepository
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.repository.IGetDataGempaRespository
import com.informasigempabumi.igmapp.ui.DataDummy
import com.informasigempabumi.igmapp.ui.home.HomeViewModel
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UseCaseIteractorTest{
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
            Assert.assertNotNull(actualDataGempa)
        } finally {
            useCaseIter.getGempaTerbaru().removeObserver(observer)
        }
    }
}