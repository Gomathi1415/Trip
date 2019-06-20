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
import com.example.trip.R
import kotlinx.android.synthetic.main.filter_dialog_fragment.*



class FilterDialogFragment : DialogFragment(){
    lateinit var filterCommunicator: FilterCommunicator
    lateinit var positiveBtn: Button
    lateinit var negativeBtn: Button
    lateinit var priceradioGroup: RadioGroup
    lateinit var ratingadioGroup: RadioGroup
    lateinit var classradioGroup: RadioGroup
    var filterPrice: Int = 0
    var filterRating: Int = 0
    var filterClass: Int = 0


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        filterCommunicator = parentFragment as FilterCommunicator

    }

    companion object {

        @JvmStatic
        fun newInstance(filterPrice: Int,filterRating : Int,filterClass:Int) = FilterDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("filterPrice", filterPrice)
                putInt("filterRating", filterRating)
                putInt("filterClass", filterClass)
            }
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.filter_dialog_fragment, null)
        priceradioGroup = view.findViewById<RadioGroup>(R.id.priceRadioGroup) as RadioGroup
        ratingadioGroup=view.findViewById(R.id.ratingRadioGroup) as RadioGroup
        classradioGroup = view.findViewById(R.id.hotelClassRadioGroup) as RadioGroup
        positiveBtn = view.findViewById<Button>(R.id.filterpositiveBtn) as Button
        negativeBtn = view.findViewById<Button>(R.id.filterNegativeBtn) as Button
        positiveBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val priceButton: Int = priceradioGroup.checkedRadioButtonId
                var filteredPrice : String = " "
                val ratingButton: Int = ratingadioGroup.checkedRadioButtonId
                var filteredrating : String = " "

                if (view.findViewById<RadioButton>(priceButton) != null) {
                    val price: RadioButton = view.findViewById(priceButton)
                    filteredPrice = price.text.toString()
                }
                if (view.findViewById<RadioButton>(ratingButton) != null) {
                    val rating: RadioButton = view.findViewById(ratingButton)
                    filteredrating = rating.text.toString()
                }
                val classButton: Int = classradioGroup.checkedRadioButtonId
                var filteredclass : String = " "
                if (view.findViewById<RadioButton>(classButton) != null) {
                    val price: RadioButton = view.findViewById(classButton)
                    filteredclass = price.text.toString()
                }

                filterCommunicator.onDialogMessege(filteredPrice,filteredrating,filteredclass)
                dismiss()
            }

        })

        negativeBtn.setOnClickListener {

            filterCommunicator.onDialogMessege(" "," "," ")
            priceradioGroup.clearCheck()
            ratingadioGroup.clearCheck()
            classradioGroup.clearCheck()

        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (filterPrice == 1) {
            below_10K.setChecked(true)
        }
        if (filterPrice == 2) {
            inbetween10k_15K.setChecked(true)
        }
        if (filterPrice == 3) {
            inbetween15k_20K.setChecked(true)
        }
        if (filterPrice == 4) {
            inbetween20k_30k.setChecked(true)
        }
        if (filterPrice == 5) {
            above_30.setChecked(true)
        }
        if (filterRating == 1) {
            oneRating.setChecked(true)
        }
        if (filterRating == 2) {
            twoRating.setChecked(true)
        }
        if (filterRating == 3) {
            threeRating.setChecked(true)
        }
        if (filterRating == 4) {
            fourRating.setChecked(true)
        }

        if (filterClass == 1) {
            twoStar.setChecked(true)
        }
        if (filterClass == 2) {
            threeStar.setChecked(true)
        }
        if (filterClass == 3) {
            fourStar.setChecked(true)
        }
        if (filterClass == 4) {
            fiveStar.setChecked(true)
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        arguments?.getInt("filterPrice")?.let {
            filterPrice = it
        }
        arguments?.getInt("filterRating")?.let {
            filterRating = it
        }
        arguments?.getInt("filterClass")?.let {
            filterClass = it
        }

    }

    interface FilterCommunicator {
        fun onDialogMessege(price: String,rating:String,hotelClass : String)
    }
}