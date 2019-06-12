package com.example.trip.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.trip.R
import kotlinx.android.synthetic.main.filter_dialog_fragment.*

class FilterDialogFragment : DialogFragment() {
    lateinit var filterCommunicator: FilterCommunicator
    lateinit var positiveBtn: Button
    lateinit var negativeBtn: Button
    lateinit var radioGroup: RadioGroup
    var filterItem: Int = 0

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        filterCommunicator = parentFragment as FilterCommunicator

    }

    companion object {

        @JvmStatic
        fun newInstance(filterItem: Int) = FilterDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("filterItem", filterItem)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.filter_dialog_fragment, null)
        radioGroup = view.findViewById<RadioGroup>(R.id.rg) as RadioGroup
        positiveBtn = view.findViewById<Button>(R.id.filterpositiveBtn) as Button
        negativeBtn = view.findViewById<Button>(R.id.filterNegativeBtn) as Button
        if (savedInstanceState != null) {
            below_10K.setChecked(savedInstanceState.getBoolean("below_10K"))
        }


        positiveBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val i: Int = radioGroup.checkedRadioButtonId
                if (view.findViewById<RadioButton>(i) != null) {
                    var rb: RadioButton = view.findViewById(i)
                    filterCommunicator.onDialogMessege(rb.text.toString())

                }
                dismiss()
            }

        })

        negativeBtn.setOnClickListener {
            isCancelable = false
            filterCommunicator.onDialogMessege("cancel")
            if (filterItem != 0) {
                radioGroup.clearCheck()
            }
            dismiss()
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (filterItem == 1) {
            below_10K.setChecked(true)
        }
        if (filterItem == 2) {
            inbetween10k_15K.setChecked(true)
        }
        if (filterItem == 3) {
            inbetween15k_20K.setChecked(true)
        }
        if (filterItem == 4) {
            inbetween20k_30k.setChecked(true)
        }
        if (filterItem == 5) {
            above_30.setChecked(true)
        }

    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        arguments?.getInt("filterItem")?.let {
            filterItem = it
        }
    }

    interface FilterCommunicator {
        fun onDialogMessege(message: String)
    }
}