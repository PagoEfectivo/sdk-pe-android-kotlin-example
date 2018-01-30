package pe.elcomercio.pagoefectivosdkkotlinsample.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_list_cips.*
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk
import pe.elcomercio.pagoefectivosdk.cip.SearchListener
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipSearch
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions.printMessageInToast
import java.util.*
import android.view.inputmethod.InputMethodManager


@Suppress("UNUSED_PARAMETER")
class ListCipsActivity : AppCompatActivity(), SearchListener {

    private val MAX_NUMBER_OF_VIEWS = 5
    private lateinit var pagoEfectivoSdk: PagoEfectivoSdk
    private lateinit var imm : InputMethodManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cips)
        init()
    }

    private fun init() {
        //Get Instance from PagoEfectivo SDK
        pagoEfectivoSdk = PagoEfectivoSdk.getInstance()

        //Init KeyBoard
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun addNewChildViews() {
        if (lnlCip.childCount < MAX_NUMBER_OF_VIEWS) {
            val textInputLayout = TextInputLayout(this)
            val editText = EditText(this)
            editText.hint = getString(R.string.new_cip)
            editText.tag = lnlCip.childCount
            editText.inputType = InputType.TYPE_CLASS_NUMBER

            textInputLayout.addView(editText)
            lnlCip.addView(textInputLayout)
            textInputLayout.requestFocus()

        } else {
            printMessageInToast(resources.getString(R.string.limit_search_alert))
        }
    }

    private fun getEditTextValues(): ArrayList<Int> {
        val cipList = ArrayList<Int>()
        for (i in 0 until lnlCip.childCount) {
            val textInputLayout = lnlCip.getChildAt(i)
            val editText = textInputLayout.findViewWithTag<EditText>(i)
            if (editText.text.toString().isEmpty()) {
                cipList.add(0)
            } else {
                cipList.add(editText.text.toString().toInt())
            }
        }
        return cipList
    }

    fun searchCipSdkOnClick(view: View) {
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        printMessageInToast(resources.getString(R.string.searching_cip))
        pagoEfectivoSdk.searchCip(getEditTextValues(), this)
    }

    fun addCipRow(view: View) {
        //Prepare focus in Keyboard
        if (lnlCip.childCount == 0) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }

        addNewChildViews()

    }

    override fun OnSearchSuccessful(listCips: List<CipSearch>) {
        val intent = Intent(this, ResultSearchCipActivity::class.java)
        intent.putExtra(getString(R.string.result_cip_list), listCips as ArrayList<CipSearch>)
        startActivity(intent)
    }

    override fun OnCipError(errorList: List<CipError>) {
        val errorMessage = StringBuilder()
        for (error in errorList) {
            errorMessage.append("- (").append(error.code).append(")").append(" | ").append(error.field).append(" --> ").append(error.message).append("\n")
        }

        printMessageInToast(errorMessage.toString())
    }

    override fun OnCipFailure(error: String) = printMessageInToast(error)

}