package pe.elcomercio.pagoefectivosdkkotlinsample.agent

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_agents.*
import kotlinx.android.synthetic.main.content_agents.*

import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentItemEntity
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentHeaderEntity
import pe.elcomercio.pagoefectivosdkkotlinsample.payment_method.PaymentMethodActivity

class AgentsActivity : AppCompatActivity() {

    private val agentAdapter = AgentAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agents)
        setSupportActionBar(toolbar)

        init()
        val bundle = intent.extras
        agentAdapter.addAgentHeader(listOf(AgentHeaderEntity(
                bundle.getInt("cip"),
                bundle.getDouble("amount").toString(),
                bundle.getString("dateExpiry"))))
        agentAdapter.addAgentItem(listOf(
                AgentItemEntity("BCP"),
                AgentItemEntity("BANBIF"),
                AgentItemEntity("BBVA"),
                AgentItemEntity("SCOTIABANK"),
                AgentItemEntity("IBK")))
    }

    private fun init() {
        if (rcvWhereToPay.adapter == null) {
            rcvWhereToPay.adapter = agentAdapter
        }
    }

    private fun startAgentActivity(it: Int) {
        val intent = Intent(this, AgentsActivity::class.java)
        intent.putExtra("paymentMethodType", it)
        startActivity(intent)
    }
}
