package com.vito.cornelius.core.coroutines.dispatchers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Identifies a dispatcher corresponding to main/UI thread operations.
 */
@Qualifier
@Retention(RUNTIME)
annotation class DispatcherMain
