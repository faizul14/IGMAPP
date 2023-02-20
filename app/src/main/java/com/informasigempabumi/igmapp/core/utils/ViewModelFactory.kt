package com.informasigempabumi.igmapp.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.informasigempabumi.igmapp.core.di.Injection
import com.informasigempabumi.igmapp.core.domain.usecase.UseCase
import com.informasigempabumi.igmapp.ui.Maps.MapsViewodel
import com.informasigempabumi.igmapp.ui.home.HomeViewModel
import com.informasigempabumi.igmapp.ui.listGMP.dirasakan.ListDirasakanViewModel
import com.informasigempabumi.igmapp.ui.listGMP.terkini.ListTerkiniViewModel

class ViewModelFactory private constructor(private val useCase: UseCase) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        const val TAG = "ViewModelFactory"

        @Volatile
        private var INCSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory = INCSTANCE ?: synchronized(this) {
            INCSTANCE ?: ViewModelFactory(Injection.provideUseCase())
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListTerkiniViewModel::class.java)) {
            return ListTerkiniViewModel(useCase) as T
        }
        if (modelClass.isAssignableFrom(ListDirasakanViewModel::class.java)) {
            return ListDirasakanViewModel(useCase) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(useCase) as T
        }
        if (modelClass.isAssignableFrom(MapsViewodel::class.java)) {
            return MapsViewodel(useCase) as T
        }

        throw IllegalArgumentException("Message ${modelClass.name}")

    }
}