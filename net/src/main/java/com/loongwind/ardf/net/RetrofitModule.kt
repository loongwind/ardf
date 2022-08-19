package com.loongwind.ardf.net

import com.google.gson.Gson
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val ARDF_GSON = "ardf_gson"
const val ARDF_BASE_URL = "ardf_base_url"
const val ARDF_DEBUG = "ardf_debug"

val retrofitModule = module {

    single(named(ARDF_GSON)) {
        Gson()
    }

    single<CookieJar> {
        HttpCookieJar()
    }

    converter{
        GsonConverterFactory.create(get(named(ARDF_GSON)))
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        val level = if (get(named(ARDF_DEBUG)))
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        interceptor.level = level
        interceptor
    }

    single {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(get<HttpLoggingInterceptor>())
            .cookieJar(get())
        val interceptors = getKoin().getAll<InterceptorKey>()
        interceptors.sortedByDescending { it.index }
            .forEach {
                builder.addInterceptor(it.interceptor)
            }
        builder
//        config.callTimeout?.let { builder.callTimeout(it, TimeUnit.SECONDS) }
//        config.connectTimeout?.let { builder.connectTimeout(it, TimeUnit.SECONDS) }
//        config.readTimeout?.let { builder.readTimeout(it, TimeUnit.SECONDS) }
//        config.writeTimeout?.let { builder.writeTimeout(it, TimeUnit.SECONDS) }
    }

    single {
        get<OkHttpClient.Builder>().build()
    }

    single {
        val builder = Retrofit.Builder()
            .baseUrl(get<String>(named(ARDF_BASE_URL)))
            .client(get())

        val converterFactories = getKoin().getAll<ConverterKey>()

        converterFactories.sortedByDescending {it.index}
            .forEach {
                    builder.addConverterFactory(it.converterFactory)
            }
        builder
    }

    single {
        get<Retrofit.Builder>().build()
    }

    single<HttpExceptionHandler> {
        DefaultHttpExceptionHandler(get())
    }

}