package pe.elcomercio.pagoefectivosdkkotlinsample

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val btnGenerate = findViewById(R.id.btnGenerate)
        btnGenerate.setOnClickListener { _ ->
            startGenerateCipActivity()
        }

        val btnSearchCips = findViewById(R.id.btnSearchCips)
        btnSearchCips.setOnClickListener { _ ->
            startListCipActivity()
        }
    }

    private fun startGenerateCipActivity() {
        val intent = Intent(this, GenerateCipActivity::class.java)
        startActivity(intent)
    }

    private fun startListCipActivity() {
        val intent = Intent(this, ListCipsActivity::class.java)
        startActivity(intent)
    }
}
