package com.vito.cornelius.core.coroutines.dispatchers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Identifies a dispatcher corresponding to CPU intensive tasks
 */
@Qualifier
@Retention(RUNTIME)
annotation class DispatcherDefault
