package com.muhammedguven.iremember.common.ui

import android.database.Cursor
import android.net.Uri
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
                val idColumn: Int? = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val nameColumn: Int? = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val uriColumn: Uri? = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                val selectionColumn: String? =
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?"

                if (idColumn != null && nameColumn != null && uriColumn != null) {
                    val id: String = cursor.getString(idColumn)
                    val name = cursor.getString(nameColumn)
                    val phoneCursor =
                        context?.contentResolver?.query(
                            uriColumn,
                            null,
                            selectionColumn,
                            arrayOf(id),
                            null
                        )
                    if (phoneCursor != null && phoneCursor.moveToNext()) {
                        val number =
                            phoneCursor.getString(
                                phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                    .orZero()
                            )
                        contacts.add(
                            Contact(
                                id = id.toLong(),
                                contactName = name,
                                contactPhoneNumber = number
                            )
                        )
                        phoneCursor.close()
                    }
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
            val id: Int = cursor.getString(callId).toInt()
            val phoneNumber: String = cursor.getString(callNumber)
            val typeOfCall: Int = cursor.getString(callType).toInt()
            val date: Date = Date(java.lang.Long.valueOf(cursor.getString(callDate)))
            val duration: String = cursor.getString(callDuration)
            val type = turnCallType(typeOfCall)

            callLogs.add(
                UserCallLog(
                    id = id,
                    phoneNumber = phoneNumber,
                    duration = duration,
                    type = type,
                    date = date
                )
            )
        }
        cursor.close()
        return callLogs
    }

    private fun turnCallType(typeOfCall: Int): String {
        return when (typeOfCall) {
            CallLog.Calls.OUTGOING_TYPE -> "OUTGOING"
            CallLog.Calls.INCOMING_TYPE -> "INCOMING"
            CallLog.Calls.MISSED_TYPE -> "MISSED"
            else -> "UNKNOWN"
        }
    }

}