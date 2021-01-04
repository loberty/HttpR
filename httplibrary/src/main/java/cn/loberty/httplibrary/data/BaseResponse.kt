package cn.loberty.httplibrary.data

/**
 * Create by WangChen on 2020/12/31
 *
 */
data class BaseResponse<T>(
    val data:T,
    val status:Int
)