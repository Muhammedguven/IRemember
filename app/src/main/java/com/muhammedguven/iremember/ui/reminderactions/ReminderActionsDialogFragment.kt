package com.muhammedguven.iremember.ui.reminderactions

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.muhammedguven.iremember.R
import com.muhammedguven.iremember.databinding.FragmentReminderActionsDialogBinding

class ReminderActionsDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentReminderActionsDialogBinding? = null
    private val binding get() = _binding!!

    private val args: ReminderActionsDialogFragmentArgs by navArgs()

    private val viewModel: ReminderActionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReminderActionsDialogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactNameTextView.text = args.name

        binding.deleteReminderButton.setOnClickListener {
            viewModel.deleteReminder(args.phoneNumber)
            dialog?.dismiss()
        }

        binding.editReminderButton.setOnClickListener {
            dialog?.dismiss()
            findNavController().navigate(
                ReminderActionsDialogFragmentDirections.openCreateReminderFragmentForEdit(
                    args.name,
                    args.phoneNumber
                )
            )
        }

        binding.resetReminderButton.setOnClickListener {
            dialog?.dismiss()
            viewModel.resetReminder(args.phoneNumber)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun getTheme(): Int = R.style.BaseBottomSheetDialog

}