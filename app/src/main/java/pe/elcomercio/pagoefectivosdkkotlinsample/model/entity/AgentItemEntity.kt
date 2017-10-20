package pe.elcomercio.pagoefectivosdkkotlinsample.model.entity

import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.AdapterConstants
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.ViewType

/**
 * Created by carlosleonardocamilovargashuaman on 10/18/17.
 */
data class AgentItemEntity(val name: String) : ViewType {
    override fun getViewType(): Int = AdapterConstants.AGENT_ITEM
}