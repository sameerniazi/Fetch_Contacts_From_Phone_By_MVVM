package com.sameer.call.sameerproject.ModelClass

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log


class ContactRepository {


    private var context: Context? = null
    private var contacts: List<Contact>? = null

    constructor(context: Context?) {
        this.context = context
        contacts = ArrayList()
    }



    fun fetchContacts(): List<Contact>? {
        var contact: Contact

        val cleanList: MutableMap<String?, Contact> = LinkedHashMap()
        val cursor = context!!.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )




        if (cursor?.count ?: 0 > 0) {

            while (cursor!!.moveToNext()) {
                contact = Contact()
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNo =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val photoUri =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))


                Log.e("contact", "getAllContacts: $name $phoneNo $photoUri")
                if (cleanList.contains(contact.getPhoneNumber())){

                }else {
                    contact.setName(name)
                    contact.setPhoneNumber(phoneNo)
                    contact.setPhotoUri(photoUri)
                    cleanList[contact.getPhoneNumber()] = contact
                }
            }
        }
        cursor?.close()
        return ArrayList(cleanList.values)
    }


    private fun clearListFromDuplicatePhoneNumber(list1: List<Contact>): List<Contact>? {
        val cleanMap: MutableMap<String?, Contact> = LinkedHashMap()
        for (i in list1.indices) {
            cleanMap[list1[i].getPhoneNumber()] = list1[i]
        }
        return ArrayList(cleanMap.values)
    }




}