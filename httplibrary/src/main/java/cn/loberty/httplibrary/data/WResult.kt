package cn.loberty.httplibrary.data

/**
 * Create by WangChen on 2021/1/4
 *
 */
sealed  class WResult<T>(val data : T? = null, val message : String? = null)  {
    class Success<T>(data: T?) : WResult<T>(data)
    class Loading<T>(data: T? = null) : WResult<T>(data)
    class Error<T>(message: String? ,data: T? = null) : WResult<T>(data,message)
}