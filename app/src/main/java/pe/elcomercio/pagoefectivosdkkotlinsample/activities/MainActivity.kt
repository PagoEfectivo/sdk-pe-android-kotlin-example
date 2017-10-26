package pe.elcomercio.pagoefectivosdkkotlinsample.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*
import pe.elcomercio.pagoefectivosdkkotlinsample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGenerate.setOnClickListener {
            startGenerateCipActivity()
        }

        btnSearchCips.setOnClickListener {
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