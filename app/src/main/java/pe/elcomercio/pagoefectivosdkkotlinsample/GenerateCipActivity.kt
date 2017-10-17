package pe.elcomercio.pagoefectivosdkkotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_generate_cip.*
import kotlinx.android.synthetic.main.content_generate_cip.*
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk
import pe.elcomercio.pagoefectivosdk.cip.CipListener
import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest
import pe.elcomercio.pagoefectivosdk.util.Currency
import pe.elcomercio.pagoefectivosdk.util.DocumentType

class GenerateCipActivity : AppCompatActivity(), CipListener {

    private val currencyValueList = listOf(
            Currency.PEN,
            Currency.USD)
    private val documentTypeValueList = listOf(
            DocumentType.DNI,
            DocumentType.LMI,
            DocumentType.NAN,
            DocumentType.PAR,
            DocumentType.PASSPORT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_cip)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val currencyNameList = listOf(
                getString(R.string.currency_pen),
                getString(R.string.currency_usd))

        val documentTypeNameList = listOf(
                getString(R.string.document_dni),
                getString(R.string.document_lmi),
                getString(R.string.document_nan),
                getString(R.string.document_par),
                getString(R.string.document_pas))

        val currencyAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyNameList)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiCurrency.adapter = currencyAdapter

        val documentTypeAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, documentTypeNameList)
        documentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spiUserDocumentType.adapter = documentTypeAdapter

        fab.setOnClickListener { _ ->
            val instance = PagoEfectivoSdk.getInstance()
            instance.generateCip(getCipRequestObject(), this)
        }
    }

    fun getCipRequestObject(): CipRequest {
        val cipRequest = CipRequest()
        cipRequest.currency = currencyValueList[spiCurrency.selectedItemPosition]
        cipRequest.amount = if (!txtAmount.text.toString().isEmpty()) txtAmount.text.toString().toDouble() else 0.00
        cipRequest.userEmail = txtUserEmail.text.toString()
        cipRequest.transactionCode = txtTransactionCode.text.toString()
        cipRequest.dateExpiry = "2017-08-24T05:40:00-05:00"
        cipRequest.additionalData = txtAdditionalData.text.toString()
        cipRequest.paymentConcept = txtPaymentConcept.text.toString()
        cipRequest.userName = txtUserName.text.toString()
        cipRequest.userLastName = txtUserLastName.text.toString()
        cipRequest.userUbigeo = txtUserUbigeo.text.toString()
        cipRequest.userCountry = txtUserCountry.text.toString()
        cipRequest.userDocumentType = documentTypeValueList[spiUserDocumentType.selectedItemPosition]
        cipRequest.userDocumentNumber = txtUserDocumentNumber.text.toString()
        cipRequest.userPhone = txtUserPhone.text.toString()
        cipRequest.userCodeCountry = txtUserCodeCountry.text.toString()
        cipRequest.adminEmail = txtAdminEmail.text.toString()
        return cipRequest
    }

    override fun OnCipFailure(p0: String?) {
        printMessageInToast("Filure Generate Cip $p0")
    }

    override fun OnCipError(p0: MutableList<CipError>?) {
        val errorMessageSyringBuilder = StringBuilder()
        for (error in p0!!) {
            errorMessageSyringBuilder
                    .append("Error")
                    .append("\n\n")
                    .append("- (")
                    .append(error.getCode()).append(")")
                    .append(" | ")
                    .append(error.getField())
                    .append(" --> ")
                    .append(error.getMessage()).append("\n")
        }
        printMessageInToast(errorMessageSyringBuilder.toString())
    }

    override fun OnCipSuccessful(p0: Cip?) {
        val cipGeneratedStringBuilder = StringBuilder()
        cipGeneratedStringBuilder.append("\n\n")
        cipGeneratedStringBuilder.append(" - CIP: ").append(p0!!.getCip()).append("\n")
        cipGeneratedStringBuilder.append(" - AMOUNT: ").append(p0.getAmount()).append("\n")
        cipGeneratedStringBuilder.append(" - CURRENCY: ").append(p0.getCurrency()).append("\n")
        cipGeneratedStringBuilder.append(" - DATEXPIRY: ").append(p0.getDateExpiry()).append("\n")
        cipGeneratedStringBuilder.append(" - TRANSACTIONCODE: ").append(p0.getTransactionCode()).append("\n")
        printMessageInToast(cipGeneratedStringBuilder.toString())
    }
}
