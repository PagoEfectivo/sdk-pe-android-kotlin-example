package pe.elcomercio.pagoefectivosdkkotlinsample.list_cip

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

class ListCipsActivity : AppCompatActivity(), SearchListener {

    private lateinit var instance: PagoEfectivoSdk
    private lateinit var searchAdapter: ListCipAdapter

    private val cipList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cips)
        init()
    }

    private fun init() {
        //Init UI
        cipList.add(System.currentTimeMillis().toInt())

        //Setup Recycler
        searchAdapter = ListCipAdapter(cipList)
        rcvSearch.adapter = searchAdapter
        rcvSearch.setHasFixedSize(true)

        //Get Instance from PagoEfectivo SDK
        instance = PagoEfectivoSdk.getInstance()
    }

    fun searchCipSdkOnClick(view: View) {
        printMessageInToast(resources.getString(R.string.searching_cip))

        val cipListToSearch = cipList.indices
                .map { rcvSearch.getChildAt(it).findViewById<EditText>(R.id.txtSearchCip) }
                .filterNot { it.text.toString().isEmpty() }
                .map { Integer.parseInt(it.text.toString()) }

        instance.searchCip(cipListToSearch, this)
    }

    fun addCipRow(view: View) {
        cipList.add(System.currentTimeMillis().toInt())
        searchAdapter.notifyDataSetChanged()
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