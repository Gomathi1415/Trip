package com.example.trip.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.trip.R


class AlertFragment : DialogFragment(){

    lateinit var positiveBtn : Button
    lateinit var negativeBtn : Button
    lateinit var alertCommunicator  : AlertCommunicator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        alertCommunicator = activity as AlertCommunicator

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.alert_fragment, null)
        positiveBtn = view.findViewById<Button>(R.id.positiveBtn) as Button
        negativeBtn = view.findViewById<Button>(R.id.negativeBtn) as Button
        isCancelable = false

        positiveBtn.setOnClickListener {

            dismiss()
            alertCommunicator.onDialogMessege(true)

        }
        negativeBtn.setOnClickListener {

            isCancelable=false
            alertCommunicator.onDialogMessege(false)

            dismiss()


        }
            return view

    }
    interface AlertCommunicator
    {
        fun onDialogMessege(message : Boolean)
    }



}