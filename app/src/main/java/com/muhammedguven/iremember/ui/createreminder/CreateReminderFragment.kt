package com.muhammedguven.iremember.ui.createreminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.muhammedguven.iremember.common.extensions.observeNonNull
import com.muhammedguven.iremember.common.ui.BaseFragment
import com.muhammedguven.iremember.databinding.FragmentCreateReminderBinding

class CreateReminderFragment : BaseFragment() {

    private var _binding: FragmentCreateReminderBinding? = null
    private val binding get() = _binding!!

    private val args: CreateReminderFragmentArgs by navArgs()

    private val createReminderViewModel: CreateReminderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateReminderBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        with(createReminderViewModel) {
            getPageViewStateLiveData().observeNonNull(viewLifecycleOwner) {
                //renderPageViewState(it)
            }
            initializeViewModel()
        }
        binding.buttonSetReminder.setOnClickListener {
            createReminderViewModel.setReminder(
                args.phoneNumber.toString(),
                binding.editTextReminderPeriod.text.toString()
            )
            findNavController().popBackStack()
        }
    }

    private fun setUpView() {
        with(binding) {
            textViewName.text = args.name
        }
    }

}