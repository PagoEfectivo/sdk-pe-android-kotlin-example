package pe.elcomercio.pagoefectivosdkkotlinsample.agent

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.AdapterConstants
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewType
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewTypeDelegateAdapter
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentHeaderEntity
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentItemEntity

/**
 * Created by carlosleonardocamilovargashuaman on 10/18/17.
 */
class WhereToPayAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapters.put(AdapterConstants.AGENT_HEADER, AgentHeaderDelegateAdapter())
        delegateAdapters.put(AdapterConstants.AGENT_ITEM, AgentItemDelegateAdapter {
            listener(it)
        })
        items = ArrayList()
    }

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])

    fun addAgentHeader(agentHeaderList: List<AgentHeaderEntity>) {
        items.addAll(agentHeaderList)
        notifyDataSetChanged()
    }

    fun addAgentItem(agentItemItemList: List<AgentItemEntity>) {
        items.addAll(agentItemItemList)
        notifyDataSetChanged()
    }
}