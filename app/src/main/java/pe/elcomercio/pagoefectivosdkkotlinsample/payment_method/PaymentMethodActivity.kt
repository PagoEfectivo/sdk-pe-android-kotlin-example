package pe.elcomercio.pagoefectivosdkkotlinsample.payment_method

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment_method.*
import kotlinx.android.synthetic.main.content_payment_method.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.agent.AgentsActivity
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.Constants
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.PaymentMethodEntity

class PaymentMethodActivity : AppCompatActivity() {

    var cip = 0
    var amount = 0.0
    var dateExpiry = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        setSupportActionBar(toolbar)

        val bundle = intent.extras
        cip = bundle.getInt(Constants.CIP_KEY)
        amount = bundle.getDouble(Constants.AMOUNT_KEY)
        dateExpiry = bundle.getString(Constants.DATE_EXPIRY_KEY)

        val paymentMethodList: MutableList<PaymentMethodEntity> = mutableListOf(
                PaymentMethodEntity(1, getString(R.string.pe_mobile_internet)),
                PaymentMethodEntity(2, getString(R.string.pe_agent_agencies)),
                PaymentMethodEntity(3, getString(R.string.pe_visa)),
                PaymentMethodEntity(4, getString(R.string.pe_master_card)),
                PaymentMethodEntity(5, getString(R.string.pe_american_express)),
                PaymentMethodEntity(6, getString(R.string.pe_dinners_club)))

        rcvPaymentMethod.adapter = PaymentMethodAdapter(paymentMethodList) {
            if (it == 1 || it == 2) {
                startAgentCipActivity(it, cip, amount, dateExpiry)
            }
        }
    }

    private fun startAgentCipActivity(it: Int, cip: Int, amount: Double, dateExpiry: String) {
        val intent = Intent(this, AgentsActivity::class.java)
        intent.putExtra(Constants.PAYMENT_METHOD_TYPE_KEY, it)
        intent.putExtra(Constants.CIP_KEY, cip)
        intent.putExtra(Constants.AMOUNT_KEY, amount)
        intent.putExtra(Constants.DATE_EXPIRY_KEY, dateExpiry)
        startActivity(intent)
    }
}
