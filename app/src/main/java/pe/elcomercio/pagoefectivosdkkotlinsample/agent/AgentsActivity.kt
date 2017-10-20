package pe.elcomercio.pagoefectivosdkkotlinsample.agent

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_agents.*
import kotlinx.android.synthetic.main.content_agents.*

import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.Constants
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentItemEntity
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentHeaderEntity

class AgentsActivity : AppCompatActivity() {

    private val agentAdapter = AgentAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agents)
        setSupportActionBar(toolbar)

        init()
        val bundle = intent.extras
        val paymentMethodType = bundle.getInt(Constants.PAYMENT_METHOD_TYPE_KEY, 0)

        agentAdapter.addAgentHeader(listOf(AgentHeaderEntity(
                bundle.getInt(Constants.CIP_KEY),
                bundle.getDouble(Constants.AMOUNT_KEY).toString(),
                bundle.getString(Constants.DATE_EXPIRY_KEY))))

        agentAdapter.addAgentItem(listOf(
                AgentItemEntity(getString(R.string.agent_bcp)),
                AgentItemEntity(getString(R.string.agent_banbif)),
                AgentItemEntity(getString(R.string.agent_bbva)),
                AgentItemEntity(getString(R.string.agent_scotiabank)),
                AgentItemEntity(getString(R.string.agent_ibk))))

        if (paymentMethodType == Constants.AGENT_TYPE) {
            agentAdapter.addAgentItem(listOf(
                    AgentItemEntity(getString(R.string.agent_casnet)),
                    AgentItemEntity(getString(R.string.agent_wester)),
                    AgentItemEntity(getString(R.string.agent_full_charge))))
        }
    }

    private fun init() {
        if (rcvWhereToPay.adapter == null) {
            rcvWhereToPay.adapter = agentAdapter
        }
    }

    private fun startPaymentDetailActivity() {
        val intent = Intent(this, AgentsActivity::class.java)
        startActivity(intent)
    }
}
