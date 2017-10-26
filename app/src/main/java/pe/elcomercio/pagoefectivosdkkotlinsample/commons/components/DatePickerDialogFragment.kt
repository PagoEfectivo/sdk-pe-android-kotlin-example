package pe.elcomercio.pagoefectivosdkkotlinsample.commons.components

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.widget.Button
import android.widget.DatePicker
import pe.elcomercio.pagoefectivosdkkotlinsample.R

/**
 * Created by carlosleonardocamilovargashuaman on 10/26/17.
 */
class DatePickerDialogFragment : DialogFragment() {

    private var mListener: DatePickerDialog.OnDateSetListener? = null

    private lateinit var datePicker: DatePicker
    private lateinit var btnCancel: Button
    private lateinit var btnOk: Button

    companion object {
        fun newInstance(): DatePickerDialogFragment = DatePickerDialogFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DatePickerDialog.OnDateSetListener) {
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_dialog_date_picker, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        datePicker = view!!.findViewById(R.id.datePicker)
        btnOk = view.findViewById(R.id.btnOk)
        btnCancel = view.findViewById(R.id.btnCancel)

        btnOk.setOnClickListener({
            dialog.dismiss()
            if (mListener != null) {
                mListener?.onDateSet(datePicker, datePicker.year, datePicker.month, datePicker.dayOfMonth)
            }
        })
        btnCancel.setOnClickListener({
            dialog.dismiss()
        })
    }

}