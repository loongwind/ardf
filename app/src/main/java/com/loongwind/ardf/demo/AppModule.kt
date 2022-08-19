package com.loongwind.ardf.demo

import com.loongwind.ardf.base.BaseViewModel
import com.loongwind.ardf.net.ARDF_BASE_URL
import com.loongwind.ardf.net.ARDF_DEBUG
import okhttp3.Interceptor
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.module.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module._singleInstanceFactory
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    // single instance of HelloRepository
//    singleOf(::HelloRepositoryImpl) { bind<HelloRepository>() }

    // ViewModel
    viewModel{ RecycleViewModel()}
    viewModel{ MultiItemViewModel()}
    viewModel{ MainViewModel()}
    viewModel{ TestViewModel()}

    single(named(ARDF_BASE_URL)) {
        "https://www.fastmock.site/mock/6d5084df89b4c7a49b28052a0f51c29a/test/"
    }

    single(named(ARDF_DEBUG)) { true }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}