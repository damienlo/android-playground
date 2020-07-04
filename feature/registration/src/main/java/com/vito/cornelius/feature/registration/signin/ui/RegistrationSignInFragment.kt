package com.vito.cornelius.feature.registration.signin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.core.android.observe
import com.vito.cornelius.core.navigation.Navigation
import com.vito.cornelius.feature.registration.R
import com.vito.cornelius.feature.registration.common.ui.highlight
import com.vito.cornelius.feature.registration.databinding.RegistrationFragmentSignInBinding
import com.vito.cornelius.feature.registration.signin.ui.model.RegistrationSignInEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationSignInFragment : Fragment() {

    private var _binding: RegistrationFragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val registrationSignInViewModel: RegistrationSignInViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationFragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewLifecycleOwner) {
            observe(registrationSignInViewModel.event, ::handleEvent)
            observe(registrationSignInViewModel.loading, ::handleLoading)
        }

        val textToHighlight = getText(R.string.registration_not_a_member_yet_text_to_highlight)
        binding.alternativeSignUpText.highlight(textToHighlight.toString()) {
            val direction =
                    RegistrationSignInFragmentDirections.toSignUpFragment()
            findNavController().navigate(direction)
        }

        binding.form.signInButton.setOnClickListener {
            registrationSignInViewModel.signInClicked(
                    email = binding.form.emailEditText.text.toString(),
                    password = binding.form.passwordEditText.text.toString()
            )
        }
    }

    private fun handleEvent(singleEvent: SingleEvent<RegistrationSignInEvent>) {
        singleEvent.getContentIfNotHandled { event ->
            when (event) {
                is RegistrationSignInEvent.Navigation -> handleNavigationEvent(event)
                is RegistrationSignInEvent.Errors -> handleErrorEvent(event)
            }
        }
    }

    private fun handleNavigationEvent(event: RegistrationSignInEvent.Navigation) {
        when (event) {
            is RegistrationSignInEvent.Navigation.NavigateToHome -> with(requireActivity()) {
                startActivity(Navigation.intentHome(this))
                finish()
            }
        }
    }

    private fun handleErrorEvent(event: RegistrationSignInEvent.Errors) {
        when (event) {
            is RegistrationSignInEvent.Errors.NoNetwork ->
                Toast.makeText(requireContext(), "No Network", Toast.LENGTH_SHORT).show()
            is RegistrationSignInEvent.Errors.BadCredentials ->
                Toast.makeText(requireContext(), "Bad Credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.viewFlipper.displayedChild = if (isLoading) VIEW_LOADING else VIEW_FORM
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val VIEW_FORM = 0
        private const val VIEW_LOADING = 1
    }
}
