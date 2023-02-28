package com.informasigempabumi.igmapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.usecase.UseCaseIteractor
import com.informasigempabumi.igmapp.ui.home.HomeViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: UseCaseIteractor
    private lateinit var viewModel: HomeViewModel
    private val dataDummy = DataDummy.getGenerateDataDummy()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `when get DataGempaTerbaru Should Not Null`() {
        val observer = Observer<DataGempa> {}
        try {
            val ecpectedDataGempa = MutableLiveData<DataGempa>()
            ecpectedDataGempa.value = dataDummy
            `when`(useCase.getGempaTerbaru()).thenReturn(ecpectedDataGempa)

            val actualDataGempa = viewModel.getGempaLive().observeForever(observer)

            Mockito.verify(useCase).getGempaTerbaru()
            Assert.assertNotNull(actualDataGempa)
        } finally {
            viewModel.getGempaLive().removeObserver(observer)
        }
    }

}
