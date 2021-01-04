package cn.loberty.httplibrary

import cn.loberty.httplibrary.gson.GsonUtil
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * retrofit 相关配置及封装
 */
class RetrofitHelper private constructor(){

    private val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonUtil.gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpHelper.instance.httpClient)

    private var defaultRetrofit: Retrofit? = null

    fun getRetrofit(): Retrofit {
        return defaultRetrofit ?: synchronized(this) {
            val hostUrl = Constants.baseUrl
            val baseUrl = if (!hostUrl.endsWith("/")) "$hostUrl/" else hostUrl
            defaultRetrofit ?: builder.baseUrl(baseUrl).build().also { defaultRetrofit = it }
        }
    }

    companion object {
        @JvmStatic
        val instance = RetrofitHelper()
    }
}