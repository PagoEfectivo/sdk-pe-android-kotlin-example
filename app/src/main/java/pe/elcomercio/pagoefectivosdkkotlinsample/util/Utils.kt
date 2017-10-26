package pe.elcomercio.pagoefectivosdkkotlinsample.util

/**
 * Created by carlosleonardocamilovargashuaman on 10/26/17.
 */
class Utils {
    companion object {
        fun addZeroToNumber(str: String): String {
            var newStr = str
            if (str.length == 1) {
                newStr = "0" + str
            }
            return newStr
        }
    }
}