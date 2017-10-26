package pe.elcomercio.pagoefectivosdkkotlinsample.commons.components

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.widget.Button
import android.widget.TimePicker
import pe.elcomercio.pagoefectivosdkkotlinsample.R

/**
 * Created by carlosleonardocamilovargashuaman on 10/26/17.
 */
class TimePickerDialogFragment : DialogFragment() {

    private var mListener: TimePickerDialog.OnTimeSetListener? = null

    private lateinit var timePicker: TimePicker
    private lateinit var btnCancel: Button
    private lateinit var btnOk: Button

    companion object {
        fun newInstance(): TimePickerDialogFragment = TimePickerDialogFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is TimePickerDialog.OnTimeSetListener) {
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.fragment_dialog_time_picker, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        timePicker = view!!.findViewById(R.id.timePicker)
        btnOk = view.findViewById(R.id.btnOk)
        btnCancel = view.findViewById(R.id.btnCancel)

        btnOk.setOnClickListener({
            dialog.dismiss()
            if (mListener != null) {
                mListener?.onTimeSet(timePicker, timePicker.currentHour, timePicker.currentMinute)
            }
        })

        btnCancel.setOnClickListener({
            dialog.dismiss()
        })
    }
}