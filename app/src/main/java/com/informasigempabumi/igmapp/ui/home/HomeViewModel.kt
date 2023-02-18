package com.informasigempabumi.igmapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.informasigempabumi.igmapp.core.domain.usecase.UseCase

class HomeViewModel(private val useCase: UseCase) : ViewModel() {
    fun getGempaLive() = useCase.getGempaTerbaru()
}