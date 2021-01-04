package cn.loberty.httpr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.loberty.httplibrary.data.WResult
import cn.loberty.httpr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initData()
    }

    private fun initData(){

        val adapter = MainAdapter(null)
        viewBinding.recyclerView.adapter = adapter

        viewModel.getMainListItem()
        viewModel.mainItemLiveData.observe(this){
            result ->
            when(result){
                is WResult.Success -> {
                    adapter.setNewInstance(result.data)
                }
            }
        }
    }

}