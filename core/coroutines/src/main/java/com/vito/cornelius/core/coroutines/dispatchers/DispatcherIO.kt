package com.vito.cornelius.core.coroutines.dispatchers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Identifies a dispatcher corresponding to input-output operations
 */
@Qualifier
@Retention(RUNTIME)
annotation class DispatcherIO
