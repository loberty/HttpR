package cn.loberty.httpr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.loberty.httplibrary.data.BaseViewModel
import cn.loberty.httplibrary.data.WResult
import kotlinx.coroutines.launch

/**
 * Create by WangChen on 2021/1/4
 *
 */
class MainViewModel : BaseViewModel() {

    private val mainRepository = MainRepository()

    private val _mainItemLiveData = MutableLiveData<WResult<MutableList<ArticleBean>>>()
    val mainItemLiveData : LiveData<WResult<MutableList<ArticleBean>>> = _mainItemLiveData
    fun getMainListItem(){
        viewModelScope.launch {
            _mainItemLiveData.value = mainRepository.getListItem()
        }
    }

}