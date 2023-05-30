package com.loongwind.ardf.net

import okhttp3.Interceptor
import org.koin.core.definition.Definition
import org.koin.core.module.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import retrofit2.Converter
import retrofit2.Retrofit

fun Module.interceptor(
    index: Int = 0,
    definition: Definition<Interceptor>
): KoinDefinition<InterceptorKey> {
    return single(named("$index")){
        InterceptorKey(index, definition(it))
    }
}

data class InterceptorKey(val index:Int, val interceptor:Interceptor)


fun Module.converter(
    index: Int = 0,
    definition: Definition<Converter.Factory>
): KoinDefinition<ConverterKey> {
    return single(named("$index")){
        ConverterKey(index, definition(it))
    }
}

data class ConverterKey(val index:Int, val converterFactory: Converter.Factory)

fun Module.baseUrl(
    definition: Definition<String>
): KoinDefinition<String> {
    return single(named(ARDF_BASE_URL), definition = definition)
}
inline fun <reified T> Module.apiService(): KoinDefinition<T> {
    return single{
        get<Retrofit>().create(T::class.java)
    }
}

fun Module.debug(
    definition: Definition<Boolean>
): KoinDefinition<Boolean> {
    return single(named(ARDF_DEBUG), definition = definition)
}