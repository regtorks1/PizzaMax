package com.example.pizzamax.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.pizzamax.databinding.FirstAlertdialogBinding

class CustomDialog : DialogFragment() {

    private lateinit var binding: FirstAlertdialogBinding

    companion object {

        const val TAG = "Custom Dialog"

        private const val PRICE = "PRICE"
        private const val ID = "ID"
        private const val NAME = "NAME"

        fun newInstance(id: String, name: String, price: String): CustomDialog {
            val args = Bundle()
            args.putString(ID, id)
            args.putString(PRICE, price)
            args.putString(NAME, name)
            val fragment = CustomDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstAlertdialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView() {
        binding.priceAlert.text = arguments?.getString(PRICE)
        binding.priceText.text = arguments?.getString(NAME)
    }

    private fun setupClickListeners() {
        binding.AddToCart.setOnClickListener {
            dismiss()
        }

        binding.cancel.setOnClickListener {
            dismiss()
        }
    }


}