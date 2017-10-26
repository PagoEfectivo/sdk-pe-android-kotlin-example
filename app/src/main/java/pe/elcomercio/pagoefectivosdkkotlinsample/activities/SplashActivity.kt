package pe.elcomercio.pagoefectivosdkkotlinsample.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pe.elcomercio.pagoefectivosdkkotlinsample.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
