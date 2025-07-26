package com.contact.contactapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.contact.contactapp.databinding.ContactItemBinding
import com.contact.contactapp.model.ContactDM

class ContactAdapter (private var contactDMS: MutableList<ContactDM>): Adapter<ContactAdapter.ContactViewHolder> (){


    companion object{
        var onContactDeleted: (( Int)->Unit)?= null

    }
    private lateinit var binding: ContactItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactDMS[position])
        binding.deleteContact.setOnClickListener{
            onContactDeleted?.invoke(position)
        }
    }
    override fun getItemCount() = contactDMS.size


    class ContactViewHolder (var binding:ContactItemBinding):ViewHolder(binding.root){
        fun bind (contactDM: ContactDM){
            binding.contactName.text = contactDM.contactName
            binding.contactNumber.text = contactDM.contactNumber
            binding.contactEmail.text = contactDM.contactEmail
            binding.contactImage.setImageURI(contactDM.contactImage)
        }
    }
}