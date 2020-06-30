package com.vito.cornelius.feature.registration.autologin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.core.navigation.Navigation
import com.vito.cornelius.feature.registration.autologin.ui.model.RegistrationAutoLoginEvent
import com.vito.cornelius.feature.registration.autologin.ui.model.RegistrationAutoLoginEvent.NavigateToHome
import com.vito.cornelius.feature.registration.autologin.ui.model.RegistrationAutoLoginEvent.NavigateToSignIn
import com.vito.cornelius.feature.registration.databinding.RegistrationFragmentAutoLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationAutoLoginFragment : Fragment() {

    private var _binding: RegistrationFragmentAutoLoginBinding? = null
    private val binding get() = _binding!!

    private val registrationAutoLoginViewModel: RegistrationAutoLoginViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationFragmentAutoLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationAutoLoginViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            handleEvent(event)
        })
    }

    private fun handleEvent(singleEvent: SingleEvent<RegistrationAutoLoginEvent>) {
        singleEvent.getContentIfNotHandled { event ->
            when (event) {
                is NavigateToHome -> {
                    startActivity(Navigation.intentHome(requireActivity()))
                    requireActivity().finish()
                }
                is NavigateToSignIn -> {
                    val direction = RegistrationAutoLoginFragmentDirections.toSignInFragment()
                    findNavController().navigate(direction)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
