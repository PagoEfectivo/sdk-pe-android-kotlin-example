package pe.elcomercio.pagoefectivosdkkotlinsample.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_where_pay.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.agent.WhereToPayAdapter
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.Constants
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentHeaderEntity
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.AgentItemEntity
import java.util.*

class WhereToPayActivity : AppCompatActivity() {

    private val typePaymentMethods = ArrayList<String>()

    private var cip = 0
    private var amount = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_where_pay)
        init()
    }

    private fun init() {

        val agentAdapter = WhereToPayAdapter {
            startPaymentDetailActivity(it)
        }

        //Type Payments
        typePaymentMethods.add(getString(R.string.title_movil))
        typePaymentMethods.add(getString(R.string.title_agents))

        //Get Cip
        val bundle = intent.extras
        val paymentMethodType = bundle.getInt(Constants.PAYMENT_METHOD_TYPE_KEY, 0)
        cip = bundle.getInt(Constants.CIP_KEY)
        amount = bundle.getDouble(Constants.AMOUNT_KEY)

        //Set title toolbar
        supportActionBar?.title = typePaymentMethods[paymentMethodType - 1]

        agentAdapter.addAgentHeader(listOf(AgentHeaderEntity(
                cip,
                amount.toString(),
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

    private fun startPaymentDetailActivity(agentName: String) {
        val intent = Intent(this, PaymentDetailActivity::class.java)
        intent.putExtra(Constants.CIP_KEY, cip)
        intent.putExtra(Constants.AMOUNT_KEY, amount)
        intent.putExtra(Constants.AGENT_NAME, agentName)
        startActivity(intent)
    }
}
