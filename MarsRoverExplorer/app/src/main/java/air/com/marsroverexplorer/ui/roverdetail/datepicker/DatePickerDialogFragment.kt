package air.com.marsroverexplorer.ui.roverdetail.datepicker

import air.com.marsroverexplorer.R
import air.com.marsroverexplorer.databinding.DatePickerViewBinding
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.date_picker_view.*
import java.util.*

class DatePickerDialogFragment: DialogFragment(), OnDatePickerViewModelDelegate {

    private lateinit var viewModel: DatePickerViewModel

    private var listener: OnDatePickeDialogFragment? = null

    companion object {
        const val CURRENT_DATE_ARGUMENT = "current_date_args"

        fun newInstance(currentDate: String, listener: OnDatePickeDialogFragment): DatePickerDialogFragment {
            val fragment = DatePickerDialogFragment()
            val bundle = Bundle()
            bundle.putString(CURRENT_DATE_ARGUMENT, currentDate)
            fragment.arguments = bundle
            fragment.listener = listener

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null && !activity!!.isFinishing) {
            activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DatePickerViewBinding = DataBindingUtil.inflate(inflater,R.layout.date_picker_view,
                                                                     container,false)

        val currentDate: String? = arguments?.getString(CURRENT_DATE_ARGUMENT, "")

        val factory = DatePickerViewModelFactory(currentDate, this)
        viewModel = ViewModelProviders.of(this, factory).get(DatePickerViewModel::class.java)
        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

        viewModel.dateLiveData.observe(viewLifecycleOwner, Observer{
            it.let {
                datePicker1.init(it.get(Calendar.YEAR), it.get(Calendar.MONTH),
                    it.get(Calendar.DAY_OF_MONTH)) { _: DatePicker, year: Int, month: Int, day: Int ->

                    viewModel.changeDate(year, month, day)
                }
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null && dialog!!.window != null) {
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun closeWithEarthDate(earthDate: String) {
        listener?.onEarthDateSelected(earthDate)
        dismiss()
    }

    override fun closeWithSolDate(solDate: String) {
        listener?.onSolDateSelected(solDate)
        dismiss()
    }

    override fun close() {
        dismiss()
    }

    interface OnDatePickeDialogFragment {
        fun onEarthDateSelected(earthDate: String)
        fun onSolDateSelected(solDate: String)
    }
}