package com.contact.contactapp.fragment

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.contact.contactapp.R
import com.contact.contactapp.databinding.FragmentAddContactBinding
import com.contact.contactapp.model.ContactDM
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddContactFragment:BottomSheetDialogFragment() {
    var onContactAdded: ((ContactDM)->Unit)?= null
    private lateinit var binding: FragmentAddContactBinding
    private var image: Uri? = null
    val intent = registerForActivityResult(ActivityResultContracts.GetContent()){
        if (it != null){
            binding.contactPictureIV.setImageURI(it)
            image = it
        }
        else{
            Toast.makeText(requireContext(),"No pic selected",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("fragment", "created fragment add contact")
        setListeners()
    }

    private fun setListeners() {
        updateStrings()
        binding.addContactBtn.setOnClickListener {
            onContactAdded?.invoke(
                ContactDM(
                    binding.contactNameTV.text.trim().toString(),
                    binding.contactNumberTV.text.trim().toString(),
                    binding.contactEmailTV.text.trim().toString(),
                    image
                )
            )
            dismiss()
        }
        binding.contactPictureIV.setOnClickListener {
            intent.launch("image/*")
        }
    }

    private fun updateStrings() {
        textChangedListener(binding.contactEmailET, binding.contactEmailTV)
        textChangedListener(binding.contactPhoneET, binding.contactNumberTV)
        textChangedListener(binding.contactNameET, binding.contactNameTV)
    }
    private fun textChangedListener(editText: EditText, textView: TextView){
        editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d ("textChangedListener","beforeTextChanged")
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textView.text = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {
                Log.d ("textChangedListener","afterTextChanged")
            }
        })
    }


    // to change white background of the dialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener{
            val bottomSheet = dialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            bottomSheet?.setBackgroundResource(R.color.transparent)
        }
        return dialog
    }
}