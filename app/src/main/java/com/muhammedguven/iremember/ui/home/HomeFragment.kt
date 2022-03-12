package com.muhammedguven.iremember.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.muhammedguven.iremember.common.extensions.observeNonNull
import com.muhammedguven.iremember.common.ui.BaseFragment
import com.muhammedguven.iremember.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    lateinit var callHistoriesAdapter: CallHistoriesAdapter

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
        checkPermissions()
        setUpView()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        with(homeViewModel) {
            getPageViewStateLiveData().observeNonNull(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            initializeViewModel()
        }
    }

    private fun setUpView() {
        binding.floatingActionButtonOpenContacts.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.openContactsFragment())
        }
        with(binding.recyclerViewCallLogList) {
            apply {
                callHistoriesAdapter = CallHistoriesAdapter()
                adapter = callHistoriesAdapter.apply {
                    //itemClickListener = ::navigateCharacterDetailFragment
                }
                //addItemDecoration(GridItemDecoration())
            }
        }
    }

    private fun renderPageViewState(viewState: CallHistoriesViewState) {
        binding.callHistoriesViewState = viewState
        callHistoriesAdapter.submitList(viewState.getCallHistories())
    }

    private fun setContactsToLocal() {
        val contacts = getContactFromUserContacts()
        contacts.isNotEmpty().let {
            homeViewModel.setContactsToLocal(contacts)
        }
    }

    private fun setCallLogsToLocal() {
        val callLogs = readCallLog()
        callLogs.isNotEmpty().let {
            homeViewModel.setCallLogsToLocal(callLogs)
        }
    }

    private fun checkPermissions() {
        if (requireContext().let {
                ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CALL_LOG)
            } != PackageManager.PERMISSION_GRANTED) {
            requestMultiplePermissions.launch(PERMISSIONS)
        } else {
            setCallLogsToLocal()
            setContactsToLocal()
        }
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.d(TAG, "${it.key} = ${it.value}")
            }
            if (permissions[PERMISSIONS[0]] == true && permissions[PERMISSIONS[1]] == true) {
                Log.d(TAG, "Permission granted")
                setCallLogsToLocal()
                setContactsToLocal()

            } else {
                Log.d(TAG, "Permission not granted")
                checkPermissions()
            }
        }

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        var PERMISSIONS = arrayOf(
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
        )
    }
}