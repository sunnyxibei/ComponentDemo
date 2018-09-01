package cn.com.timeriver.girls.main

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import cn.com.timeriver.common.data.bean.Girl
import cn.com.timeriver.girls.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

class GirlAdapter(context: Context?) : RecyclerArrayAdapter<Girl>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> = GirlViewHolder(parent, R.layout.item_girl)

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.itemView.setOnClickListener { listener.invoke(position, holder) }
    }

    private lateinit var listener: (position: Int, holder: BaseViewHolder<*>) -> Unit

    fun setOnItemClickListener(listener: (position: Int, holder: BaseViewHolder<*>) -> Unit) {
        this.listener = listener
    }
}

class GirlViewHolder(parent: ViewGroup?, res: Int) : BaseViewHolder<Girl>(parent, res) {
    private var image: ImageView = itemView.findViewById(R.id.girl_img)

    override fun setData(data: Girl?) {
        super.setData(data)
        Glide.with(context)
                .load(data?.url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image)
    }
}
