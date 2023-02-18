package com.informasigempabumi.igmapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.informasigempabumi.igmapp.core.domain.usecase.UseCase

class HomeViewModel(private val useCase: UseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getGempaLive() = useCase.getGempaTerbaru()
}