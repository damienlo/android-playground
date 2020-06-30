package com.vito.cornelius.feature.registration.common.ui

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.vito.cornelius.feature.registration.R

internal fun TextView.highlight(key: String, clickAction: () -> Unit) {
    val color = ContextCompat.getColor(this.context, R.color.primary)
    val startIndex = this.text.indexOf(key)
    val endIndex = this.text.length
    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            clickAction.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = true
        }
    }
    val spannableText = SpannableString(this.text).apply {
        setSpan(RelativeSizeSpan(1.2f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setSpan(
                ForegroundColorSpan(color), startIndex, endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    }
    movementMethod = LinkMovementMethod.getInstance()
    this.text = spannableText
}