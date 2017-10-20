package pe.elcomercio.pagoefectivosdkkotlinsample.agent

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view_agent_item.view.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewType
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewTypeDelegateAdapter
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions.inflate
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentItemEntity

/**
 * Created by carlosleonardocamilovargashuaman on 10/18/17.
 *
 */
class AgentItemDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = AgentViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, viewType: ViewType) {
        holder as AgentViewHolder
        holder.bind(viewType as AgentItemEntity)
    }

    inner class AgentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_view_agent_item)) {

        private val lblTitle = itemView.lblTitle

        fun bind(agentItemEntity: AgentItemEntity) {
            lblTitle.text = agentItemEntity.name
        }
    }
}