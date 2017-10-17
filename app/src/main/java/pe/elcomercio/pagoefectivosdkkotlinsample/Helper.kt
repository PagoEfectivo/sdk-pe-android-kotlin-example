package pe.elcomercio.pagoefectivosdkkotlinsample

import android.content.Context
import android.widget.Toast

/**
 * Created by carlosleonardocamilovargashuaman on 10/17/17.
 */

fun Context.printMessageInToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

