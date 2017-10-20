package pe.elcomercio.pagoefectivosdkkotlinsample.list_cip

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions.inflate

/**
 * Created by tohure on 20/10/17.
 */
class ListCipAdapter(private val cipList: List<Int>) : RecyclerView.Adapter<ListCipAdapter.ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder =
            ListItemViewHolder(parent.inflate(R.layout.item_edittext))

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) = Unit

    override fun getItemCount(): Int = cipList.size

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
