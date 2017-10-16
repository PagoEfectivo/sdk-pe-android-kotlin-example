package pe.elcomercio.pagoefectivosdkkotlinsample

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk
import pe.elcomercio.pagoefectivosdk.cip.CipListener
import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest
import pe.elcomercio.pagoefectivosdk.util.Currency

class GenerateCipActivity : AppCompatActivity(), CipListener {

    private var spiCurrency: Spinner? = null
    private var txtAmount: EditText? = null
    private var txtTransactionCode: EditText? = null
    private var txtDateExpiry: EditText? = null
    private var txtAdditionalData: EditText? = null
    private var txtPaymentConcept: EditText? = null
    private var txtUserEmail: EditText? = null
    private var txtUserName: EditText? = null
    private var txtUserLastName: EditText? = null
    private var txtUserUbigeo: EditText? = null
    private var txtUserCountry: EditText? = null
    private var txtUserDocumentType: EditText? = null
    private var txtUserDocumentNumber: EditText? = null
    private var txtUserPhone: EditText? = null
    private var txtUserCodeCountry: EditText? = null
    private var txtAdminEmail: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_cip)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //Views
        spiCurrency = findViewById(R.id.spiCurrency) as Spinner
        txtAmount = findViewById(R.id.txtAmount) as EditText
        txtTransactionCode = findViewById(R.id.txtTransactionCode) as EditText
        txtDateExpiry = findViewById(R.id.txtDateExpiry) as EditText
        txtAdditionalData = findViewById(R.id.txtAdditionalData) as EditText
        txtPaymentConcept = findViewById(R.id.txtPaymentConcept) as EditText
        txtUserEmail = findViewById(R.id.txtUserEmail) as EditText
        txtUserName = findViewById(R.id.txtUserName) as EditText
        txtUserLastName = findViewById(R.id.txtUserLastName) as EditText
        txtUserUbigeo = findViewById(R.id.txtUserUbigeo) as EditText
        txtUserCountry = findViewById(R.id.txtUserCountry) as EditText
        txtUserDocumentType = findViewById(R.id.txtUserDocumentType) as EditText
        txtUserDocumentNumber = findViewById(R.id.txtUserDocumentNumber) as EditText
        txtUserPhone = findViewById(R.id.txtUserPhone) as EditText
        txtUserCodeCountry = findViewById(R.id.txtUserCodeCountry) as EditText
        txtAdminEmail = findViewById(R.id.txtAdminEmail) as EditText

        val currencyList = listOf(Currency.PEN, Currency.USD)

        val currencyAdapter = ArrayAdapter<Currency>(this, android.R.layout.simple_spinner_item, currencyList)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiCurrency!!.adapter = currencyAdapter


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

    /*fun getCipRequestObject(): CipRequest {

        var number = 0
        val cipRequest = CipRequest()

        number = spiCurrency!!.selectedItemPosition

        if (number == 0) {
            cipRequest.currency = Currency.PEN
        } else {
            cipRequest.currency = Currency.USD
        }

        cipRequest.amount = txtAmount!!.text.toString().toDouble()
        cipRequest.transactionCode = txtT!!.text.toString().toDouble()
        cipRequest.dateExpiry = txtAmount!!.text.toString().toDouble()
        cipRequest.additionalData = txtAmount!!.text.toString().toDouble()
        cipRequest.paymentConcept = txtAmount!!.text.toString().toDouble()

        cipRequest.userEmail = txtAmount!!.text.toString().toDouble()
        cipRequest.userName = txtAmount!!.text.toString().toDouble()
        cipRequest.userLastName = txtAmount!!.text.toString().toDouble()
        cipRequest.userUbigeo = txtAmount!!.text.toString().toDouble()
        cipRequest.userCountry = txtAmount!!.text.toString().toDouble()
        cipRequest.userDocumentType = txtAmount!!.text.toString().toDouble()



    }*/

    override fun OnCipFailure(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnCipError(p0: MutableList<CipError>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun OnCipSuccessful(p0: Cip?) {
        Log.e("CIP RESULT ", "CIP RESULT ${p0?.cip}")
    }
}
