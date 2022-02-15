package view.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hwangduil.accountbook.R
import dto.AccountDto

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val index = itemView.findViewById<TextView>(R.id.recyclerIndexText)
    private val classify = itemView.findViewById<TextView>(R.id.recyclerClassifyText)
    private val date = itemView.findViewById<TextView>(R.id.recyclerDateText)
    private val amounts = itemView.findViewById<TextView>(R.id.recyclerAmountsText)

    fun binding(dto: AccountDto?) {
        classify.text = dto?.classify
        date.text = dto?.date
        amounts.text = dto?.amount.toString()
    }

}