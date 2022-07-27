package com.loongwind.ardf.demo

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // single instance of HelloRepository
//    singleOf(::HelloRepositoryImpl) { bind<HelloRepository>() }

    // ViewModel
    viewModel{ RecycleViewModel()}
    viewModel{ MultiItemViewModel()}
    viewModel{ MainViewModel()}
    viewModel{ TestViewModel()}
}