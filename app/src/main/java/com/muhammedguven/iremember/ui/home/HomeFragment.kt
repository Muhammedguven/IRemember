package com.muhammedguven.iremember.ui.home

import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.muhammedguven.iremember.common.extensions.orZero
import com.muhammedguven.iremember.databinding.FragmentHomeBinding
import com.muhammedguven.iremember.ui.contacts.model.Contact
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkReadContactsPermission()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    override fun onResume() {
        super.onResume()
        checkReadContactsPermission()
    }

    private fun setUpViewModel() {
        with(homeViewModel) {
            initializeViewModel()
        }

    }

    private fun setUpView() {
        binding.floatingActionButtonOpenContacts.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.openContactsFragment())
        }
    }

    private fun checkReadContactsPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), 100)
        } else {
            getContactFromUserContacts()
        }
    }

    private fun getContactFromUserContacts() {
        val uri = ContactsContract.Contacts.CONTENT_URI
        val sort: String = Phone.DISPLAY_NAME + " ASC"
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
                val uriPhone = Phone.CONTENT_URI
                val selection = Phone.CONTACT_ID + " =?"
                val phoneCursor =
                    context?.contentResolver?.query(uriPhone, null, selection, arrayOf(id), null)

                if (phoneCursor!!.moveToNext()) {
                    val number =
                        phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER).orZero())
                    val contact =
                        Contact(id = id.toLong(), contactName = name, contactPhoneNumber = number)
                    contacts.add(contact)
                    phoneCursor.close()
                }
            }
            cursor.close()

            onContactListReady(contacts)
        } else {
            Toast.makeText(requireActivity(), "Bir hata oluştu", Toast.LENGTH_LONG).show()
        }
    }

    private fun onContactListReady(contacts: List<Contact>) {
        homeViewModel.setContactsToLocal(contacts)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContactFromUserContacts()
        } else {
            Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_LONG)
                .show()//Onay vermeden appi kullanamazsınız popupu
            checkReadContactsPermission()
        }
    }
}