package com.randomuser.app.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.randomuser.app.R
import com.randomuser.app.data.User
import com.randomuser.app.fragments.list.ListingScreenUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val user = arguments?.getParcelable<User>("user")

        return ComposeView(requireContext()).apply {
            setContent {
                user?.let {
                    UserDetailUI(it)
                }
            }
        }
    }
}