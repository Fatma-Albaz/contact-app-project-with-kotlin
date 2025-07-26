package com.contact.contactapp.repository

import com.contact.contactapp.model.ContactDM

class ContactRepository {
    companion object{
        var numOfContacts=0;
        var contactsDMS = mutableListOf<ContactDM>()
        fun addContact(currentContactDM: ContactDM):Unit{
            if (numOfContacts>=6){
                return
            }
            contactsDMS.add(currentContactDM)
            numOfContacts++
        }
        fun checkEmptyContactList():Boolean = numOfContacts==0
        fun removeContactAtPosition (position: Int){
            if (position>= numOfContacts){
                return
            }
            contactsDMS.removeAt(position)
            numOfContacts--
        }
        fun removeAllContacts():Unit{
            contactsDMS.clear()
            numOfContacts=0
        }
        fun getContacts ():MutableList<ContactDM> = contactsDMS

    }
}