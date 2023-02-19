package com.informasigempabumi.igmapp.ui.Maps

import androidx.lifecycle.*
import com.informasigempabumi.igmapp.core.domain.model.DataGempa
import com.informasigempabumi.igmapp.core.domain.usecase.UseCase
import kotlinx.coroutines.launch

class MapsViewodel(private val useCase: UseCase): ViewModel() {
   fun getDataCombine() = useCase.getDataCombine()
}