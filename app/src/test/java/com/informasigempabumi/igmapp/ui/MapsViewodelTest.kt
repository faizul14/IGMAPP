package com.informasigempabumi.igmapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.usecase.UseCaseIteractor
import com.informasigempabumi.igmapp.ui.Maps.MapsViewodel
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
class MapsViewodelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: UseCaseIteractor
    private lateinit var viewModel: MapsViewodel
    private val dataDummy = DataDummy.getListGenerateDataDummy()

    @Before
    fun setUp() {
        viewModel = MapsViewodel(useCase)
    }

    @Test
    fun `when get DataGempaCombine Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<DataGempa>>()
            ecpectedDataGempa.value = dataDummy
            Mockito.`when`(useCase.getDataCombine()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = viewModel.getDataCombine().observeForever(observer)

            Mockito.verify(useCase).getDataCombine()
            Assert.assertNotNull(actualDataGempa)
        } finally {
            viewModel.getDataCombine().removeObserver(observer)
        }
    }
}