package tw.com.cathaybankproject.areadetail

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
import tw.com.cathaybankproject.model.ZooPlant

class ZooPlantListAdapter(context: Context): RecyclerView.Adapter<ZooPlantListAdapter.ZooPlantViewHolder>()  {
    var context: Context = context
    var onItemClick: ((ZooPlant) -> Unit)? = null
    private var plantList: ArrayList<ZooPlant> = arrayListOf()

    fun setPlantList(list: ArrayList<ZooPlant>?) {
        plantList = list!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZooPlantViewHolder {
        val cell = LayoutInflater.from(context).inflate(R.layout.zoo_plant_list_item, parent, false)
        return ZooPlantViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: ZooPlantViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.nameCh
        holder.tvAlso.text = item.alsoKnown
        Glide.with(context).load(item.picUrl).into(holder.ivThumb)
    }

    private fun getItem(position: Int): ZooPlant {
        return plantList[position]
    }

    inner class ZooPlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_title)
        var ivThumb: ImageView = itemView.findViewById(R.id.iv_thumb)
        var tvAlso: TextView = itemView.findViewById(R.id.tv_also)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(plantList[adapterPosition])
            }
        }
    }
}