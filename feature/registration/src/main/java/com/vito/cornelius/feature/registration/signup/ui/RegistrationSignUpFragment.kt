package com.vito.cornelius.feature.registration.signup.ui

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
import com.vito.cornelius.domain.common.model.ErrorCause
import com.vito.cornelius.domain.common.model.Resource
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewLifecycleOwner) {
            observe(registrationSignUpViewModel.event, ::handleEvent)
            observe(registrationSignUpViewModel.status, ::handleStatus)
        }
    }

    private fun handleStatus(signUpStatus: Resource<Unit>) {
        when (signUpStatus) {
            is Resource.Success ->
                binding.viewFlipper.displayedChild = VIEW_FORM
            is Resource.Loading ->
                binding.viewFlipper.displayedChild = VIEW_LOADING
            is Resource.Error<*> -> {
                binding.viewFlipper.displayedChild = VIEW_ERROR
                handleErrorStatus(signUpStatus.error)
            }
        }
    }

    private fun handleErrorStatus(errorCause: ErrorCause) {
        when (errorCause) {
            is ErrorCause.NetworkNotAvailable ->
                Toast.makeText(requireContext(), "No Network", Toast.LENGTH_SHORT).show()
            is ErrorCause.ServerError ->
                Toast.makeText(requireContext(), "No Network", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleEvent(singleEvent: SingleEvent<RegistrationSignUpEvent>) {
        singleEvent.getContentIfNotHandled { event ->
            when (event) {
                is RegistrationSignUpEvent.Navigation -> handleNavigationEvent(event)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val VIEW_FORM = 0
        private const val VIEW_LOADING = 1
        private const val VIEW_ERROR = 1
    }
}
