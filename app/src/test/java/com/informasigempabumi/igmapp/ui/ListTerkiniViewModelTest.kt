package com.informasigempabumi.igmapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.usecase.UseCaseIteractor
import com.informasigempabumi.igmapp.ui.listGMP.terkini.ListTerkiniViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListTerkiniViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: UseCaseIteractor
    private lateinit var viewModel: ListTerkiniViewModel
    private val dataDummy = DataDummy.getListGenerateDataDummy()

    @Before
    fun setUp() {
        viewModel = ListTerkiniViewModel(useCase)
    }

    @Test
    fun `when get ListDataGempaTerkini Should Not Null`() {
        val observer = Observer<List<DataGempa>> {}
        try {
            val ecpectedDataGempa = MutableLiveData<List<DataGempa>>()
            ecpectedDataGempa.value = dataDummy
            Mockito.`when`(useCase.getGempaTerkini()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = viewModel.getListDataGempaTerkini().observeForever(observer)

            Mockito.verify(useCase).getGempaTerkini()
            assertNotNull(actualDataGempa)
        } finally {
            viewModel.getListDataGempaTerkini().removeObserver(observer)
        }
    }
}