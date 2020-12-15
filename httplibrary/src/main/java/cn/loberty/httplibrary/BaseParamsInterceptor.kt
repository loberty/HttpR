package cn.loberty.httplibrary

import okhttp3.*
import okio.Buffer

class BaseParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        //url重新构建，在get请求的时候在url后面拼入公共参数,生成新的HttpUrl
        var httpUrl = oldRequest.url
        //在post请求的时候，加入公共参数
        var requestBody = oldRequest.body
        //获取公共参数
        val baseParameters = getBaseParameters()

        when (oldRequest.method) {
            "GET" -> {
                val httpUrlBuilder = httpUrl.newBuilder()
                //将所有公共参数添加到url中
                for ((key, value) in baseParameters) {
                    httpUrlBuilder.addQueryParameter(key, value)
                }
            }
            "POST" -> {
                val oldBody = requestBody
                if (oldBody is FormBody) {
                    requestBody = FormBody.Builder().let {
                        //加入公共参数
                        for ((key, value) in baseParameters) {
                            if (value != null) {
                                it.addEncoded(key, value)
                            }
                        }
                        //加入原request中的参数
                        val size = oldBody.size
                        for (index in 0 until size) {
                            it.add(oldBody.name(index), oldBody.value(index))
                        }
                        it.build()
                    }
                    val buffer = Buffer()
                    requestBody.writeTo(buffer)
                    buffer.close()
                }
            }
        }
        val requestBuilder = oldRequest.newBuilder()
        val request = requestBuilder.method(oldRequest.method, requestBody)
                .url(httpUrl).build()
        return chain.proceed(request)
    }

    private fun getBaseParameters(): MutableMap<String, String?> {
        val data: MutableMap<String, String?> = mutableMapOf()
        data["device_type"] = "android"
        return data
    }
}