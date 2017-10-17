package pe.elcomercio.pagoefectivosdkkotlinsample.payment_method

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment_method.*
import kotlinx.android.synthetic.main.content_payment_method.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R

class PaymentMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        setSupportActionBar(toolbar)

        val paymentMethodList = listOf(
                getString(R.string.pe_mobile_internet),
                getString(R.string.pe_agent_agencies),
                getString(R.string.pe_visa),
                getString(R.string.pe_master_card),
                getString(R.string.pe_american_express),
                getString(R.string.pe_dinners_club))
        rcvPaymentMethod.adapter = PaymentMethodAdapter(paymentMethodList){
            startListCipActivity()
        }
    }

    private fun startListCipActivity() {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        startActivity(intent)
    }

}
