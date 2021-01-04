package cn.loberty.httpr

import cn.loberty.httplibrary.data.BaseResponse
import retrofit2.http.GET

/**
 * Create by WangChen on 2021/1/4
 *
 */
interface MainApi {

    @GET("/api/v2/categories/Article")
    suspend fun getListItem() : BaseResponse<MutableList<ArticleBean>>

}