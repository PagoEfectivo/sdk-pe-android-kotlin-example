package pe.elcomercio.pagoefectivosdkkotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment_detail.*
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.Constants

class PaymentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_detail)
        init()
    }

    private fun init() {

        val bundle = intent.extras
        val cip = bundle.getInt(Constants.CIP_KEY)
        val amount = bundle.getDouble(Constants.AMOUNT_KEY)
        val agentName = bundle.getString(Constants.AGENT_NAME)

        val titleSummary = String.format(getString(R.string.title_summary_payment), amount.toString(), agentName)
        val stepOneSummary = String.format(getString(R.string.step_one_for_payment), agentName)
        val stepThreeSummary = String.format(getString(R.string.step_three_for_payment), cip.toString())

        lblTitleSummary.text = titleSummary
        lblNearAgent.text = stepOneSummary
        lblCipCode.text = stepThreeSummary
    }
}