package pe.elcomercio.pagoefectivosdkkotlinsample.payment_method

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.content_payment_method.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R

class PaymentMethodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

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
