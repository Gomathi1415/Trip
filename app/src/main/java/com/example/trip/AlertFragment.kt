package com.example.trip

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.alert_fragment.*
import kotlin.concurrent.thread


class AlertFragment : DialogFragment(){

    lateinit var positiveBtn : Button
    lateinit var negativeBtn : Button
    lateinit var alertCommunicator  :AlertCommunicator

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