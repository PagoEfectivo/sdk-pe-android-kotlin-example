package pe.elcomercio.pagoefectivosdkkotlinsample.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_generate_cip.*
import kotlinx.android.synthetic.main.content_generate_cip.*
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk
import pe.elcomercio.pagoefectivosdk.cip.CipListener
import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest
import pe.elcomercio.pagoefectivosdk.util.Currency
import pe.elcomercio.pagoefectivosdk.util.DocumentType
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.adapters.Constants
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.components.DatePickerDialogFragment
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.components.TimePickerDialogFragment
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions.printMessageInToast
import pe.elcomercio.pagoefectivosdkkotlinsample.util.Utils
import java.util.*

class GenerateCipActivity : AppCompatActivity(), CipListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private val calendar = Calendar.getInstance()

    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    private var hourOfDay = calendar.get(Calendar.HOUR)
    private var minute = calendar.get(Calendar.MINUTE)

    private lateinit var currencyNameList: List<String>
    private lateinit var documentTypeNameList: List<String>

    private lateinit var currencyValueList: List<Currency>
    private lateinit var documentTypeValueList: List<DocumentType>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_cip)
        init()
        btnGenerateCip.setOnClickListener {
            printMessageInToast(resources.getString(R.string.generating_cip))
            val instance = PagoEfectivoSdk.getInstance()
            instance.generateCip(getCipRequestObject(), this)
        }
    }

    private fun init() {
        initCurrencyValues()
        initCurrencyNames()
        initDocumentTypeValues()
        initDocumentTypeNames()

        //Get Instance from PagoEfectivo SDK

        //Setting Up currency adapter
        val currencyAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyNameList)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiCurrency.adapter = currencyAdapter

        //Setting Up documentType adapter
        val documentTypeAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, documentTypeNameList)
        documentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiUserDocumentType.adapter = documentTypeAdapter

        setCurrentDate(this.year, this.month, this.dayOfMonth)
        setCurrentTime(this.hourOfDay, this.minute)

    }

    private fun initCurrencyValues() {
        currencyValueList = listOf(
                Currency.PEN,
                Currency.USD)
    }

    private fun initCurrencyNames() {
        currencyNameList = listOf(
                getString(R.string.currency_pen),
                getString(R.string.currency_usd))
    }

    private fun initDocumentTypeValues() {
        documentTypeValueList = listOf(
                DocumentType.DNI,
                DocumentType.LIBRETA_MILITAR,
                DocumentType.OTROS,
                DocumentType.PARTIDA_NACIMIENTO,
                DocumentType.PASSPORT)
    }

    private fun initDocumentTypeNames() {
        documentTypeNameList = listOf(
                getString(R.string.document_dni),
                getString(R.string.document_lmi),
                getString(R.string.document_nan),
                getString(R.string.document_par),
                getString(R.string.document_pas))
    }

    private fun setCurrentDate(year: Int, month: Int, dayOfMonth: Int) {
        lblDateExpiry.text = getString(R.string.dateformat_with_values,
                Utils.addZeroToNumber(year.toString()),
                Utils.addZeroToNumber((month + 1).toString()),
                dayOfMonth.toString())
    }

    private fun setCurrentTime(hourOfDay: Int, minute: Int) {
        val pm_am = if (hourOfDay <= 12) {
            "AM"
        } else {
            "PM"
        }
        lblTimeExpiry.text = getString(R.string.timeformat_with_values,
                Utils.addZeroToNumber(hourOfDay.toString()),
                Utils.addZeroToNumber(minute.toString()),
                pm_am)
    }

    private fun getCipRequestObject(): CipRequest {
        val cipRequest = CipRequest()
        cipRequest.currency = currencyValueList[spiCurrency.selectedItemPosition]
        cipRequest.amount = if (!txtAmount.text.toString().isEmpty()) txtAmount.text.toString().toDouble() else 0.00
        cipRequest.userEmail = txtUserEmail.text.toString()
        cipRequest.transactionCode = txtTransactionCode.text.toString()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, this.year)
        calendar.set(Calendar.MONTH, this.month)
        calendar.set(Calendar.DATE, this.dayOfMonth)

        calendar.set(Calendar.HOUR_OF_DAY, this.hourOfDay)
        calendar.set(Calendar.MINUTE, this.minute)
        calendar.set(Calendar.SECOND, 0)

        cipRequest.dateExpiry = calendar.time
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

        if (!txtAdminEmail.text.toString().isEmpty()) {
            cipRequest.adminEmail = txtAdminEmail.text.toString()
        }

        return cipRequest
    }

    override fun OnCipFailure(p0: String?) = printMessageInToast("Filure Generate Cip $p0")

    override fun OnCipError(p0: MutableList<CipError>?) {
        val errorMessageSyringBuilder = StringBuilder()
        for (error in p0!!) {
            errorMessageSyringBuilder
                    .append("Error")
                    .append("\n\n")
                    .append("- (")
                    .append(error.code).append(")")
                    .append(" | ")
                    .append(error.field)
                    .append(" --> ")
                    .append(error.message).append("\n")
        }
        printMessageInToast(errorMessageSyringBuilder.toString())
    }

    override fun OnCipSuccessful(p0: Cip?) {
        val cipGeneratedStringBuilder = StringBuilder()
        cipGeneratedStringBuilder.append("\n\n")
        cipGeneratedStringBuilder.append(" - CIP: ").append(p0!!.cip).append("\n")
        cipGeneratedStringBuilder.append(" - AMOUNT: ").append(p0.amount).append("\n")
        cipGeneratedStringBuilder.append(" - CURRENCY: ").append(p0.currency).append("\n")
        cipGeneratedStringBuilder.append(" - DATEXPIRY: ").append(p0.dateExpiry).append("\n")
        cipGeneratedStringBuilder.append(" - TRANSACTIONCODE: ").append(p0.transactionCode).append("\n")
        startListCipActivity(p0)
    }

    private fun startListCipActivity(p0: Cip?) {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        intent.putExtra(Constants.CIP_KEY, p0!!.cip)
        intent.putExtra(Constants.AMOUNT_KEY, p0.amount)
        intent.putExtra(Constants.DATE_EXPIRY_KEY, p0.dateExpiry)
        startActivity(intent)
    }

    fun showDatePickerDialog(view: View) {
        val datePickerDialogFragment = DatePickerDialogFragment.newInstance()
        datePickerDialogFragment.show(supportFragmentManager, "date_picker_dialog_fragment")
    }

    fun showTimePickerDialog(view: View) {
        val timePickerDialogFragment = TimePickerDialogFragment.newInstance()
        timePickerDialogFragment.show(supportFragmentManager, "time_picker_dialog_fragment")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.year = year
        this.month = month
        this.dayOfMonth = dayOfMonth
        setCurrentDate(this.year, this.month, this.dayOfMonth)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.hourOfDay = hourOfDay
        this.minute = minute
        setCurrentTime(this.hourOfDay, this.minute)
    }
}