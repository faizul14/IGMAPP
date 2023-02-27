package com.informasigempabumi.igmapp.ui.di

import com.informasigempabumi.igmapp.core.domain.usecase.UseCase
import com.informasigempabumi.igmapp.core.domain.usecase.UseCaseIteractor
import com.informasigempabumi.igmapp.ui.Maps.MapsViewodel
import com.informasigempabumi.igmapp.ui.home.HomeViewModel
import com.informasigempabumi.igmapp.ui.listGMP.dirasakan.ListDirasakanViewModel
import com.informasigempabumi.igmapp.ui.listGMP.terkini.ListTerkiniViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UseCase> { UseCaseIteractor(get()) }
}

val viewModelModule = module {
    viewModel {
        ListTerkiniViewModel(get())
    }
    viewModel {
        ListDirasakanViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        MapsViewodel(get())
    }

}