package com.sameer.call.sameerproject.ViewModel

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import com.sameer.call.sameerproject.ModelClass.Contact
import com.sameer.call.sameerproject.ModelClass.ContactRepository

class ContactViewModel: BaseObservable {
    private var contacts: ObservableArrayList<Contact>? = null
    private var repository: ContactRepository? = null
    constructor(applicationContext: Context?)
    {   contacts = ObservableArrayList<Contact>()
        repository = ContactRepository(applicationContext)

    }




    fun getContacts(): List<Contact>? {
        contacts!!.addAll(repository!!.fetchContacts()!!)
        return contacts
    }
}