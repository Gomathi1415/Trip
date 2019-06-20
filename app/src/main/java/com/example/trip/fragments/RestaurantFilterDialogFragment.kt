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
import kotlinx.android.synthetic.main.restaurant_dialog_filter.*


class RestaurantFilterDialogFragment : DialogFragment(){
    lateinit var filterCommunicator: RestaurantFilterCommunicator
    lateinit var positiveBtn: Button
    lateinit var negativeBtn: Button

    lateinit var ratingadioGroup: RadioGroup
    lateinit var dietradioGroup: RadioGroup

    var filterRating: Int = 0
    var filterDiet: Int = 0


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        filterCommunicator = parentFragment as RestaurantFilterCommunicator

    }

    companion object {

        @JvmStatic
        fun newInstance(filterRating : Int,filterDiet:Int) = RestaurantFilterDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("filterRating", filterRating)
                putInt("filterDiet", filterDiet)
            }
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.restaurant_dialog_filter, null)

        ratingadioGroup=view.findViewById(R.id.restRatingRadioGroup) as RadioGroup
        dietradioGroup = view.findViewById(R.id.restaurantDietType) as RadioGroup
        positiveBtn = view.findViewById<Button>(R.id.filterpositiveBtn) as Button
        negativeBtn = view.findViewById<Button>(R.id.filterNegativeBtn) as Button
        positiveBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


                val ratingButton: Int = ratingadioGroup.checkedRadioButtonId
                var filteredrating : String = " "

                if (view.findViewById<RadioButton>(ratingButton) != null) {
                    val rating: RadioButton = view.findViewById(ratingButton)
                    filteredrating = rating.text.toString()
                }
                val dietButton: Int = dietradioGroup.checkedRadioButtonId
                var filtereddiet : String = " "
                if (view.findViewById<RadioButton>(dietButton) != null) {
                    val price: RadioButton = view.findViewById(dietButton)
                    filtereddiet = price.text.toString()
                }

                filterCommunicator.onDialogMessege(filteredrating,filtereddiet)
                dismiss()
            }

        })

        negativeBtn.setOnClickListener {

            filterCommunicator.onDialogMessege(" "," ")

            ratingadioGroup.clearCheck()
            dietradioGroup.clearCheck()

        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (filterRating == 1) {
            RoneRating.setChecked(true)
        }
        if (filterRating == 2) {
            RtwoRating.setChecked(true)
        }
        if (filterRating == 3) {
            RthreeRating.setChecked(true)
        }
        if (filterRating == 4) {
            RfourRating.setChecked(true)
        }

        if (filterDiet == 1) {
            veg.setChecked(true)
        }
        if (filterDiet == 2) {
            nonVeg.setChecked(true)
        }
        if (filterDiet == 3) {
            both.setChecked(true)
        }

    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

        arguments?.getInt("filterRating")?.let {
            filterRating = it
        }
        arguments?.getInt("filterDiet")?.let {
            filterDiet = it
        }

    }

    interface RestaurantFilterCommunicator {
        fun onDialogMessege(rating:String,dietType : String)
    }
}