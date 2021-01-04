package cn.loberty.httpr

import cn.loberty.httplibrary.RetrofitHelper

/**
 * Create by WangChen on 2021/1/4
 *
 */
class ApiFactory private constructor() {

    companion object {
        private var instance: ApiFactory? = null

        fun getInstance(): ApiFactory {
            return instance ?: synchronized(this) {
                instance ?: ApiFactory().also {
                    instance = it
                }
            }
        }
    }

    private var mainApi : MainApi? = null
    fun getMainApi(): MainApi {
        return mainApi ?: synchronized(this) {
            mainApi ?: RetrofitHelper.instance.getRetrofit().create(MainApi::class.java)
                .also { mainApi = it }
        }
    }


}