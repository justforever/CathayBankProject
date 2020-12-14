package tw.com.cathaybankproject.area

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tw.com.cathaybankproject.R
import tw.com.cathaybankproject.model.ZooArea

class ZooAreaListAdapter(context: Context): RecyclerView.Adapter<ZooAreaListAdapter.ZooAreaViewHolder>() {
    private val context: Context = context
    private var zooAreaList: MutableList<ZooArea> = mutableListOf()
    var onItemClick: ((ZooArea) -> Unit)? = null

    fun setZooAreaList(list: MutableList<ZooArea>) {
        zooAreaList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZooAreaViewHolder {
        val cell = LayoutInflater.from(context).inflate(R.layout.zoo_list_item, parent, false)
        return ZooAreaViewHolder(cell)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) 0 else 1
//        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ZooAreaViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = "${item.getName()}\n(${item.getCategory()})"
        Glide.with(context).load(item.getPicUrl()).into(holder.ivThumb)
    }

    override fun getItemCount(): Int {
        return zooAreaList.size
    }

    private fun getItem(position: Int): ZooArea {
        return zooAreaList[position]
    }

    inner class ZooAreaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_zoo_name)
        var ivThumb: ImageView = itemView.findViewById(R.id.iv_thumb)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(zooAreaList[adapterPosition])
            }
        }
    }
}