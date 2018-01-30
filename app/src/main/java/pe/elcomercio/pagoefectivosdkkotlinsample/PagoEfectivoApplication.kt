package pe.elcomercio.pagoefectivosdkkotlinsample

import android.app.Application
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk

/**
 * Created by carlosleonardocamilovargashuaman on 10/12/17.
 */
class PagoEfectivoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val pagoEfectivoSdk = PagoEfectivoSdk.inicialize(this)
        pagoEfectivoSdk.setServiceId(10)
        pagoEfectivoSdk.setAccessKey("AKIPJP77AHN2DKVIJPR1")
        pagoEfectivoSdk.setSecretKey("Nfybo8h0yN7CFN1ycX+XaG99pj/y3Vt25urt1PR1")
        pagoEfectivoSdk.setSandBox(true)
    }
}