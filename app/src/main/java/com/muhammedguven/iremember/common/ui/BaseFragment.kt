package com.muhammedguven.iremember.common.ui

import android.database.Cursor
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import com.muhammedguven.iremember.common.extensions.orZero
import com.muhammedguven.iremember.ui.contacts.model.Contact

abstract class BaseFragment : Fragment() {

    fun getContactFromUserContacts(): List<Contact> {
        val uri = ContactsContract.Contacts.CONTENT_URI
        val sort: String = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        val cursor: Cursor? = context?.contentResolver?.query(uri, null, null, null, sort)
        val contacts: MutableList<Contact> = mutableListOf()

        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID).orZero()
                )
                val name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME).orZero()
                )
                val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                val selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"
                val phoneCursor =
                    context?.contentResolver?.query(uriPhone, null, selection, arrayOf(id), null)

                if (phoneCursor!!.moveToNext()) {
                    val number =
                        phoneCursor.getString(
                            phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                .orZero()
                        )
                    val contact =
                        Contact(id = id.toLong(), contactName = name, contactPhoneNumber = number)
                    contacts.add(contact)
                    phoneCursor.close()
                }
            }
            cursor.close()

        }
        return contacts
    }

}