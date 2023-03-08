package com.informasigempabumi.igmapp.ui.Maps

import androidx.lifecycle.ViewModel
import com.informasigempabumi.igmapp.core.domain.usecase.UseCase

class MapsViewodel(private val useCase: UseCase) : ViewModel() {
    fun getDataCombine() = useCase.getDataCombine()
}