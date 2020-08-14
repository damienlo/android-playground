@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.vito.cornelius.ui.illustration.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.vito.cornelius.ui.illustration.view.databinding.IllustrationWithCtaViewBinding

class IllustrationView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: IllustrationWithCtaViewBinding =
            IllustrationWithCtaViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.IllustrationView,
                0, 0
        ).apply {
            try {
                getString(R.styleable.IllustrationView_illuTitle)?.let { title ->
                    setTitle(title)
                }
                getString(R.styleable.IllustrationView_illuSubTitle)?.let { subTitle ->
                    setSubTitle(subTitle)
                }
                getString(R.styleable.IllustrationView_illuCtaText)?.let { subTitle ->
                    setCtaText(subTitle)
                }
                val drawableRes = getResourceId(R.styleable.IllustrationView_illuDrawable, -1)
                if (drawableRes > 0) {
                    setIllustration(drawableRes)
                }
            } finally {
                recycle()
            }
        }
    }

    fun setTitle(text: String) {
        binding.title.text = text
    }

    fun setTitle(@StringRes textResId: Int) {
        binding.title.setText(textResId)
    }

    fun setSubTitle(text: String) {
        binding.subTitle.text = text
    }

    fun setSubTitle(@StringRes textResId: Int) {
        binding.subTitle.setText(textResId)
    }

    fun setIllustration(@DrawableRes drawableRes: Int) {
        binding.imageView.setImageResource(drawableRes)
    }

    fun setCtaText(@StringRes textResId: Int) {
        binding.ctaButton.setText(textResId)
    }

    fun setCtaText(text: String) {
        binding.ctaButton.text = text
    }

    fun setCtaOnClickListener(action: () -> Unit) {
        binding.ctaButton.setOnClickListener { action.invoke() }
    }
}