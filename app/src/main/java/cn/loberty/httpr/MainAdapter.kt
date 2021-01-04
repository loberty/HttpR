package cn.loberty.httpr

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Create by WangChen on 2021/1/4
 *
 */
class MainAdapter(dateList: MutableList<ArticleBean>?) :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.layout_item, dateList) {

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        holder.setText(R.id.txt, item.title)
        holder.setText(R.id.txt_des, item.desc)
        Glide.with(context).load(item.coverImageUrl).into(holder.getView(R.id.img))
    }
}