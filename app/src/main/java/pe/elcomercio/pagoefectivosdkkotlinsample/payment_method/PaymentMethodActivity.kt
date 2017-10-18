package pe.elcomercio.pagoefectivosdkkotlinsample.payment_method

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment_method.*
import kotlinx.android.synthetic.main.content_payment_method.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.model.entity.PaymentMethodEntity

class PaymentMethodActivity : AppCompatActivity() {

    var cip = 0
    var amount = ""
    var dateExpiry = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        setSupportActionBar(toolbar)

        val bundle = intent.extras
        cip = bundle.getInt("")
        amount = bundle.getString("")
        dateExpiry = bundle.getString("")

        val paymentMethodList = listOf(
                PaymentMethodEntity(1, getString(R.string.pe_mobile_internet)),
                PaymentMethodEntity(2, getString(R.string.pe_agent_agencies)),
                PaymentMethodEntity(3, getString(R.string.pe_visa)),
                PaymentMethodEntity(4, getString(R.string.pe_master_card)),
                PaymentMethodEntity(5, getString(R.string.pe_american_express)),
                PaymentMethodEntity(6, getString(R.string.pe_dinners_club)))

        rcvPaymentMethod.adapter = PaymentMethodAdapter(paymentMethodList) {
            startListCipActivity(it, cip, amount, dateExpiry)
        }
    }

    private fun startListCipActivity(it: Int, cip: Int, amount: String, dateExpiry: String) {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        intent.putExtra("paymentMethodType", it)
        intent.putExtra("cip", cip)
        intent.putExtra("amount", amount)
        intent.putExtra("dateExpiry", dateExpiry)
        startActivity(intent)
    }

}
