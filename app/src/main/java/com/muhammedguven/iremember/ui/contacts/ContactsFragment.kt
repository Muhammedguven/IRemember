package com.muhammedguven.iremember.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.muhammedguven.iremember.common.extensions.observeNonNull
import com.muhammedguven.iremember.common.ui.BaseFragment
import com.muhammedguven.iremember.databinding.FragmentContactsBinding
import com.muhammedguven.iremember.ui.contacts.model.Contact

class ContactsFragment : BaseFragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ContactsViewModel by activityViewModels()

    lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    private fun setUpViewModel() {

        with(listViewModel) {
            getPageViewStateLiveData().observeNonNull(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            initializeViewModel()
        }
    }

    private fun setUpView() {
        with(binding.recyclerViewContactList) {
            apply {
                contactsAdapter = ContactsAdapter()
                adapter = contactsAdapter.apply {
                    itemClickListener = ::navigateCreateReminderFragment
                }
                //addItemDecoration(GridItemDecoration())
            }
        }
    }

    private fun renderPageViewState(viewState: ContactsViewState) {
        binding.contactsViewState = viewState
        contactsAdapter.submitList(viewState.getContacts())
    }

    private fun navigateCreateReminderFragment(contact: Contact) {
        findNavController().navigate(
            ContactsFragmentDirections.openCreateReminderFragment(
                contact.contactName,
                contact.contactPhoneNumber
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}