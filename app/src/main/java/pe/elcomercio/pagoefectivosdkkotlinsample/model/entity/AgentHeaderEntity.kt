package pe.elcomercio.pagoefectivosdkkotlinsample.model.entity

import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.AdapterConstants
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewType

/**
 * Created by carlosleonardocamilovargashuaman on 10/18/17.
 */
data class AgentHeaderEntity(
        val cipNumber: Int,
        val amount: String,
        val dateTime: String) : ViewType {
    override fun getViewType(): Int = AdapterConstants.AGENT_HEADER
}