package com.vito.cornelius.core.navigation

import android.content.Context
import android.content.Intent

object Navigation {

    fun intentRegistration(context: Context): Intent =
            internalIntent(context, "feature.registration.RegistrationActivity")

    fun intentHome(context: Context): Intent =
            internalIntent(context, "feature.home.HomeActivity")

    private fun internalIntent(context: Context, action: String): Intent =
            Intent(action).setPackage(context.packageName)
}
