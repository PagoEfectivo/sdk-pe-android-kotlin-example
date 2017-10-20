package pe.elcomercio.pagoefectivosdkkotlinsample.agent

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_where_pay.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.Constants
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentHeaderEntity
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentItemEntity
import java.util.*

class WhereToPayActivity : AppCompatActivity() {

    private val agentAdapter = AgentAdapter()
    private val typePaymentMethods = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_where_pay)
        init()
    }

    private fun init() {

        //Type Payments
        typePaymentMethods.add(getString(R.string.title_movil))
        typePaymentMethods.add(getString(R.string.title_agents))

        //Get Cip
        val bundle = intent.extras
        val paymentMethodType = bundle.getInt(Constants.PAYMENT_METHOD_TYPE_KEY, 0)

        //Set title toolbar
        if (supportActionBar != null) {
            supportActionBar!!.title = typePaymentMethods[paymentMethodType - 1]
        }

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

        if (rcvWhereToPay.adapter == null) {
            rcvWhereToPay.adapter = agentAdapter
        }
        rcvWhereToPay.setHasFixedSize(true)
        rcvWhereToPay.addItemDecoration(DividerItemDecoration(rcvWhereToPay.context, DividerItemDecoration.VERTICAL))
    }

    private fun startPaymentDetailActivity() {
        val intent = Intent(this, WhereToPayActivity::class.java)
        startActivity(intent)
    }
}
