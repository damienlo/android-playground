package com.vito.cornelius.core.android

import java.util.concurrent.atomic.AtomicBoolean

open class SingleEvent<out T>(private val content: T) {

    val hasBeenHandled = AtomicBoolean(false)

    fun getContentIfNotHandled(handleContent: (T) -> Unit) {
        consume { handleContent(content) }
    }

    fun peekContent() = content

    private fun consume(callback: (T) -> Unit) {
        if (!hasBeenHandled.get()) {
            hasBeenHandled.set(true)
            callback.invoke(content)
        }
    }

    // This is important for testing, we do not want to compare the SingleEvent itself,
    // we're just interested in the content being equal.
    // If we want to actually compare the SingleEvent we'll need some kind of
    // single event factory and this will add unnecessary complexity.
    override fun equals(other: Any?): Boolean {
        return (other as? SingleEvent<*>)?.let {
            content?.equals(it.content)
        } ?: false
    }
}
