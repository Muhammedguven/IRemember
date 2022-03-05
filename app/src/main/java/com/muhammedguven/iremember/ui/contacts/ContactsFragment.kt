package com.muhammedguven.iremember.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.muhammedguven.iremember.common.extensions.observeNonNull
import com.muhammedguven.iremember.databinding.FragmentContactsBinding
import com.muhammedguven.iremember.ui.contacts.model.Contact

class ContactsFragment : Fragment() {

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
        with(binding.recyclerViewCharacterList) {
            apply {
                contactsAdapter = ContactsAdapter()
                adapter = contactsAdapter.apply {
                    //itemClickListener = ::navigateCharacterDetailFragment
                }
                //addItemDecoration(GridItemDecoration())
            }
        }
    }

    private fun renderPageViewState(viewState: ContactsViewState) {
        binding.contactsViewState = viewState
        contactsAdapter.submitList(viewState.getContacts())
    }

    private fun navigateCharacterDetailFragment(contact: Contact) {
        // findNavController().navigate(ContactListFragmentDirections.openCharacterDetail(contact.id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}