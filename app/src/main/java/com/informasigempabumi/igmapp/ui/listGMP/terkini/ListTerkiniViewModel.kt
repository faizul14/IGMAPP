package com.informasigempabumi.igmapp.ui.listGMP.terkini

import androidx.lifecycle.ViewModel
import com.informasigempabumi.igmapp.core.domain.usecase.UseCase

class ListTerkiniViewModel(private val useCase: UseCase) : ViewModel(){
    fun getListDataGempaTerkini() = useCase.getGempaTerkini()
}