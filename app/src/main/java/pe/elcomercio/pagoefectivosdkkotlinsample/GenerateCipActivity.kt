package pe.elcomercio.pagoefectivosdkkotlinsample

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk
import pe.elcomercio.pagoefectivosdk.cip.CipListener
import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest
import pe.elcomercio.pagoefectivosdk.util.Currency

class GenerateCipActivity : AppCompatActivity(), CipListener {
    override fun OnCipFailure(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnCipError(p0: MutableList<CipError>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun OnCipSuccessful(p0: Cip?) {
        Log.e("CIP RESULT ", "CIP RESULT ${p0?.cip}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_cip)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        val currencyList = listOf("Carlitos ", "SALUDOS")


        val spiCurrency = findViewById(R.id.spiCurrency) as Spinner


        val currencyAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyList)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiCurrency.adapter = currencyAdapter


        //val list = resources.getStringArray(R.array.month)


        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->

            //Log.e("VEAMOS ","VEAMOS ${list.size}")

            /*val instance = PagoEfectivoSdk.getInstance()


            val cipRequest = CipRequest()
            cipRequest.currency = Currency.PEN
            cipRequest.amount = 22.65
            cipRequest.transactionCode = "101"
            cipRequest.userEmail = "carlosleonardo@gmail.com"

            instance.generateCip(cipRequest, this)*/

        }
    }
}
