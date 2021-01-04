package cn.loberty.httpr

import cn.loberty.httplibrary.data.BaseRepository
import cn.loberty.httplibrary.data.WResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Create by WangChen on 2021/1/4
 *
 */
class MainRepository : BaseRepository() {

    suspend fun getListItem() : WResult<MutableList<ArticleBean>>{
        return withContext(Dispatchers.IO){
            getApiResult {
                ApiFactory.getInstance().getMainApi().getListItem()
            }
        }
    }

}