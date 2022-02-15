package view.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hwangduil.accountbook.R
import dto.AccountDto

class CustomAdapter(val context: Context, private val dataList: ArrayList<AccountDto>) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}