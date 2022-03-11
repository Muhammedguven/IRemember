package com.muhammedguven.iremember.common.ui

import android.database.Cursor
import android.provider.CallLog
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import com.muhammedguven.iremember.common.extensions.orZero
import com.muhammedguven.iremember.ui.contacts.model.Contact
import com.muhammedguven.iremember.ui.home.model.UserCallLog
import java.util.Date

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

    fun readCallLog(): List<UserCallLog> {
        val cursor: Cursor? = requireContext().contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            null, null, null, CallLog.Calls.DATE + " DESC"
        )
        val callId = cursor!!.getColumnIndex(CallLog.Calls._ID)
        val callNumber = cursor.getColumnIndex(CallLog.Calls.NUMBER)
        val callType = cursor.getColumnIndex(CallLog.Calls.TYPE)
        val callDate = cursor.getColumnIndex(CallLog.Calls.DATE)
        val callDuration = cursor.getColumnIndex(CallLog.Calls.DURATION)
        val callLogs: MutableList<UserCallLog> = mutableListOf()

        while (cursor.moveToNext()) {
            val phoneNumber = cursor.getString(callNumber)
            val callType = cursor.getString(callType)
            val date = cursor.getString(callDate)
            val callDayTime = Date(java.lang.Long.valueOf(date))
            val duration = cursor.getString(callDuration)
            val id = cursor.getString(callId).toInt()
            var type = ""
            when (callType.toInt()) {
                CallLog.Calls.OUTGOING_TYPE -> type = "OUTGOING"
                CallLog.Calls.INCOMING_TYPE -> type = "INCOMING"
                CallLog.Calls.MISSED_TYPE -> type = "MISSED"
            }

            callLogs.add(
                UserCallLog(
                    id = id,
                    phoneNumber = phoneNumber,
                    duration = duration,
                    type = type,
                    date = callDayTime
                )
            )
        }
        /* for (i in callLogs){
             Log.d("MY_APP", "${i.id},${i.phoneNumber}, ${i.duration},${i.type},${i.date}")
         }*/
        cursor.close()
        return callLogs
    }

}