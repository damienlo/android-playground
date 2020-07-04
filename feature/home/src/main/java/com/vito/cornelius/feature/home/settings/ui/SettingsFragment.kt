package com.vito.cornelius.feature.home.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.core.android.observe
import com.vito.cornelius.core.navigation.Navigation
import com.vito.cornelius.feature.home.databinding.FragmentSettingsBinding
import com.vito.cornelius.feature.home.settings.ui.model.SettingsEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewLifecycleOwner) {
            observe(settingsViewModel.event, ::handleEvent)
            observe(settingsViewModel.isDarkThemeLive, ::handleDarkTheme)
        }

        binding.darkThemeSwitch.setOnCheckedChangeListener { _, checked ->
            settingsViewModel.updateDarkThemeStatus(checked)
        }

        binding.logoutButton.setOnClickListener {
            settingsViewModel.logoutButtonClicked()
        }
    }

    private fun handleDarkTheme(isDarkThemeEnabled: Boolean) {
        binding.darkThemeSwitch.isChecked = isDarkThemeEnabled
    }

    private fun handleEvent(singleEvent: SingleEvent<SettingsEvent>) {
        singleEvent.getContentIfNotHandled { event ->
            when (event) {
                is SettingsEvent.Navigation -> handleNavigationEvent(event)
            }
        }
    }

    private fun handleNavigationEvent(event: SettingsEvent.Navigation) {
        when (event) {
            is SettingsEvent.Navigation.NavigateToRegistration -> with(requireActivity()) {
                startActivity(Navigation.intentRegistration(this))
                finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

