package com.vito.cornelius.feature.registration.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.core.navigation.Navigation
import com.vito.cornelius.feature.registration.R
import com.vito.cornelius.feature.registration.common.ui.highlight
import com.vito.cornelius.feature.registration.databinding.RegistrationFragmentSignUpBinding
import com.vito.cornelius.feature.registration.signup.ui.model.RegistrationSignUpEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationSignUpFragment : Fragment() {

    private var _binding: RegistrationFragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val registrationSignUpViewModel: RegistrationSignUpViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationFragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationSignUpViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            handleEvent(event)
        })
        registrationSignUpViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            handleLoading(isLoading)
        })

        val textToHighlight =
                getText(R.string.registration_already_a_member_sign_in_text_to_highlight)
        binding.alternativeSignInText.highlight(textToHighlight.toString()) {
            val direction =
                    RegistrationSignUpFragmentDirections.toSignInFragment()
            findNavController().navigate(direction)
        }

        binding.form.signUpButton.setOnClickListener {
            registrationSignUpViewModel.signUpClicked(
                    name = binding.form.nameEditText.text.toString(),
                    email = binding.form.emailEditText.text.toString(),
                    password = binding.form.passwordEditText.text.toString()
            )
        }
    }

    private fun handleEvent(singleEvent: SingleEvent<RegistrationSignUpEvent>) {
        singleEvent.getContentIfNotHandled { event ->
            when (event) {
                is RegistrationSignUpEvent.Navigation -> handleNavigationEvent(event)
                is RegistrationSignUpEvent.Errors -> handleErrorEvent(event)
            }
        }
    }

    private fun handleNavigationEvent(event: RegistrationSignUpEvent.Navigation) {
        when (event) {
            is RegistrationSignUpEvent.Navigation.NavigateToHome -> with(requireActivity()) {
                startActivity(Navigation.intentHome(this))
                finish()
            }
        }
    }

    private fun handleErrorEvent(event: RegistrationSignUpEvent.Errors) {
        when (event) {
            is RegistrationSignUpEvent.Errors.NoNetwork ->
                Toast.makeText(requireContext(), "No Network", Toast.LENGTH_SHORT).show()
            is RegistrationSignUpEvent.Errors.Unexpected ->
                Toast.makeText(requireContext(), "Unexpected error", Toast.LENGTH_SHORT).show()
            is RegistrationSignUpEvent.Errors.EmailAlreadyExist ->
                Toast.makeText(requireContext(), "Email already exist", Toast.LENGTH_SHORT).show()
            is RegistrationSignUpEvent.Errors.PasswordTooWeak ->
                Toast.makeText(requireContext(), "Password too weak", Toast.LENGTH_SHORT).show()
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
