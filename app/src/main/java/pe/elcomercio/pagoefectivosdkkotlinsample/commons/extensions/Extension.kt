package pe.elcomercio.pagoefectivosdkkotlinsample.commons.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by carlosleonardocamilovargashuaman on 10/17/17.
 */

fun Context.printMessageInToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)




