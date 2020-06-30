package com.vito.cornelius.feature.home.users.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vito.cornelius.feature.home.R
import com.vito.cornelius.feature.home.databinding.FragmentUserListBinding
import com.vito.cornelius.feature.home.users.list.UserListAdapter.UserSelectedListener
import com.vito.cornelius.feature.home.users.model.UserUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val userListViewModel: UserListViewModel by viewModels()

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        postponeEnterTransition()
        binding.recyclerview.doOnPreDraw {
            startPostponedEnterTransition()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usersAdapter =
                UserListAdapter(
                        UserSelectedListenerImpl()
                )
        val spacePx = resources.getDimensionPixelSize(R.dimen.keyline_2)
        with(binding.recyclerview) {
            adapter = usersAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(
                    com.vito.cornelius.feature.home.users.list.GridSpacingItemDecoration(
                            spanCount = 2,
                            spacing = spacePx,
                            includeEdge = true
                    )
            )
        }

        userListViewModel.users.observe(viewLifecycleOwner, Observer { users ->
            usersAdapter.setData(users)
        })
    }

    private inner class UserSelectedListenerImpl : UserSelectedListener {
        override fun onUserSelected(user: UserUiModel, imageView: ImageView) {
            val direction =
                    UserListFragmentDirections.toUserDetailsFragment(user.userId, user.avatar ?: "")
            if (user.avatar.isNullOrEmpty()) {
                findNavController().navigate(direction)
            } else {
                val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)
                findNavController().navigate(direction, extras)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

