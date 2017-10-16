package pe.elcomercio.pagoefectivosdkkotlinsample

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.content_generate_cip.*
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk
import pe.elcomercio.pagoefectivosdk.cip.CipListener
import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest
import pe.elcomercio.pagoefectivosdk.util.Currency
import pe.elcomercio.pagoefectivosdk.util.DocumentType

class GenerateCipActivity : AppCompatActivity(), CipListener {

    private val currencyList = listOf(Currency.PEN, Currency.USD)
    private val documentTypeList = listOf(DocumentType.DNI, DocumentType.LMI, DocumentType.NAN,
            DocumentType.PAR, DocumentType.PASSPORT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_cip)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val currencyAdapter = ArrayAdapter<Currency>(this, android.R.layout.simple_spinner_item, currencyList)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiCurrency!!.adapter = currencyAdapter


        val documentTypeAdapter = ArrayAdapter<DocumentType>(this, android.R.layout.simple_spinner_item, documentTypeList)
        documentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiUserDocumentType!!.adapter = documentTypeAdapter

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { _ ->
            val instance = PagoEfectivoSdk.getInstance()
            //val cipRequest = getCipRequestObject()
            val cipRequest = CipRequest()
            cipRequest.currency = Currency.PEN
            cipRequest.amount = 22.65
            cipRequest.transactionCode = "101"
            cipRequest.userEmail = "carlosleonardo@gmail.com"

            instance.generateCip(cipRequest, this)
        }
    }

    fun getCipRequestObject(): CipRequest {
        val cipRequest = CipRequest()
        cipRequest.currency = getCurrency(spiCurrency.selectedItemPosition)
        cipRequest.amount = if (!txtAmount.text.toString().isEmpty()) txtAmount.text.toString().toDouble() else 0.00
        cipRequest.transactionCode = txtTransactionCode.text.toString()
        cipRequest.dateExpiry = txtAmount.text.toString()
        cipRequest.additionalData = txtAdditionalData.text.toString()
        cipRequest.paymentConcept = txtPaymentConcept.text.toString()
        cipRequest.userEmail = txtUserEmail.text.toString()
        cipRequest.userName = txtUserName.text.toString()
        cipRequest.userLastName = txtUserLastName.text.toString()
        cipRequest.userUbigeo = txtUserUbigeo.text.toString()
        cipRequest.userCountry = txtUserCountry.text.toString()
        cipRequest.userDocumentType = getDocumentType(spiUserDocumentType.selectedItemPosition)
        cipRequest.userDocumentNumber = txtUserDocumentNumber.text.toString()
        cipRequest.userPhone = txtUserPhone.text.toString()
        cipRequest.userCodeCountry = txtUserCodeCountry.text.toString()
        cipRequest.adminEmail = txtAdminEmail.text.toString()


        return cipRequest
    }

    private fun getCurrency(itemPosition: Int): Currency = currencyList[itemPosition]

    private fun getDocumentType(itemPosition: Int): DocumentType = documentTypeList[itemPosition]

    override fun OnCipFailure(p0: String?) {
        Toast.makeText(this, "QUE1", Toast.LENGTH_SHORT).show()
        Log.e("FAILURE ", "FAILURE $p0")
    }

    override fun OnCipError(p0: MutableList<CipError>?) {
        Toast.makeText(this, "QUE2", Toast.LENGTH_SHORT).show()
        Log.e("CIP ERROR ", "CIP ERROR ${p0!![0]}")
    }

    override fun OnCipSuccessful(p0: Cip?) {
        Toast.makeText(this, "QUE3", Toast.LENGTH_SHORT).show()
        Log.e("CIP RESULT ", "CIP RESULT ${p0?.cip}")
    }
}
