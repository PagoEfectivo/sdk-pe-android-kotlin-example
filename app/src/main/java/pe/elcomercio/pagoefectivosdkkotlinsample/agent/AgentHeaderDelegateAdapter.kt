package pe.elcomercio.pagoefectivosdkkotlinsample.agent

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view_agent_header.view.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewType
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewTypeDelegateAdapter
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions.inflate
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentHeaderEntity

/**
 * Created by carlosleonardocamilovargashuaman on 10/18/17.
 *
 */
class AgentHeaderDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            AgentHeaderViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, viewType: ViewType) {
        holder as AgentHeaderViewHolder
        holder.bind(viewType as AgentHeaderEntity)
    }

    inner class AgentHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_view_agent_header)) {
        private val lblCip = itemView.lblCip
        private val lblAmount = itemView.lblAmount
        private val lblDateTime = itemView.lblDateExpiry

        fun bind(agentHeaderEntity: AgentHeaderEntity) {
            lblCip.text = agentHeaderEntity.cipNumber.toString()
            lblAmount.text = agentHeaderEntity.amount
            lblDateTime.text = agentHeaderEntity.dateTime
        }
    }
}