package com.randomuser.app.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.randomuser.app.R
import com.randomuser.app.utils.showInfoAlertDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingOfUsersFragment : Fragment() {

    private val viewModel: ListingOfUsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initObservers()

        return ComposeView(requireContext()).apply {
            setContent {
                ListingScreenUI(viewModel
                ) { dest, bundle -> findNavController().navigate(dest, bundle) }
            }
        }
    }

    private fun initObservers() {
        viewModel.getShowNoInternet().observe(viewLifecycleOwner, {
            if (it) {
                viewModel.setShowNoInternet(false)
                showInfoAlertDialog(requireActivity(), getString(R.string.no_internet), getString(R.string.check_connection))
            }
        })
    }
}