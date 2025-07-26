package com.contact.contactapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.contact.contactapp.R
import com.contact.contactapp.adapter.ContactAdapter
import com.contact.contactapp.databinding.ActivityContactBinding
import com.contact.contactapp.fragment.AddContactFragment
import com.contact.contactapp.repository.ContactRepository

class ContactActivity: AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private val fragmentInstance = AddContactFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContactBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        setRecyclerView()
        initListeners()
        setViewsDetails()
    }

    private fun setViewsDetails() {
        if (ContactRepository.numOfContacts==0){
            binding.deleteContact.visibility= View.INVISIBLE
            binding.contactsRecyclerView.visibility = View.INVISIBLE
            binding.noContactImage.visibility = View.VISIBLE
            binding.noContactText.visibility = View.VISIBLE
        }
        else{
            binding.contactsRecyclerView.visibility = View.VISIBLE
            binding.deleteContact.visibility= View.VISIBLE
            binding.noContactImage.visibility = View.INVISIBLE
            binding.noContactText.visibility = View.INVISIBLE
        }
        if (ContactRepository.numOfContacts>=6){
            binding.addContact.visibility= View.INVISIBLE
        }
        else{
            binding.addContact.visibility= View.VISIBLE
        }
        // Load the gif from drawable
        Glide.with(this)
            .asGif()
            .load(R.drawable.no_contacts) // your gif file name
            .into(binding.noContactImage)
    }

    private fun setRecyclerView() {
        val layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        binding.contactsRecyclerView.layoutManager = layoutManager
        binding.contactsRecyclerView.adapter = ContactAdapter(ContactRepository.contactsDMS)
        setViewsDetails()

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initListeners() {
        binding.addContact.setOnClickListener { showFragment() }
        binding.deleteContact.setOnClickListener {
            ContactRepository.removeAllContacts()
            binding.contactsRecyclerView.adapter?.notifyDataSetChanged()
            setViewsDetails()
        }
        fragmentInstance.onContactAdded={contact->
            ContactRepository.addContact(contact)
            binding.contactsRecyclerView.adapter?.notifyItemChanged(ContactRepository.numOfContacts)
            setViewsDetails()
        }
        ContactAdapter.onContactDeleted = { position: Int ->
            ContactRepository.removeContactAtPosition(position)
            binding.contactsRecyclerView.adapter?.notifyItemChanged(position)
            setViewsDetails()
        }
    }

    private fun showFragment() {
        fragmentInstance.show(supportFragmentManager, "string")
    }
}