package cn.loberty.httplibrary.data

/**
 * Create by WangChen on 2020/12/31
 *
 */
abstract class BaseRepository {

    protected suspend fun <T> getApiResult(call:suspend() -> BaseResponse<T> ) : WResult<T>{

        return try {
            val result = call()
            return if (result.status == 100){
                WResult.Success(result.data)
            }else{
                WResult.Error(result.status.toString(),result.data)
            }
        }catch (e:Throwable){
            WResult.Error(e.message)
        }

    }

}