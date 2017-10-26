package pe.elcomercio.pagoefectivosdkkotlinsample.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result_search_cip.*
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipSearch
import pe.elcomercio.pagoefectivosdkkotlinsample.R
import pe.elcomercio.pagoefectivosdkkotlinsample.activities.adapters.ResultSearchAdapter
import java.util.*

class ResultSearchCipActivity : AppCompatActivity() {

    private lateinit var searchList: List<CipSearch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_search_cip)
        init()
    }

    private fun init() {

        if (intent != null && intent.hasExtra(getString(R.string.result_cip_list))) {
            searchList = intent.getSerializableExtra(getString(R.string.result_cip_list)) as ArrayList<CipSearch>
        }

        if (!searchList.isEmpty()) {
            val resultSearchAdapter = ResultSearchAdapter(searchList)

            //Setup Recycler
            rcvResultSearchCip.adapter = resultSearchAdapter
            rcvResultSearchCip.setHasFixedSize(true)
        }
    }
}