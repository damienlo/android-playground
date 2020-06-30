package com.vito.cornelius.feature.home.users

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.load(
        url: String,
        @DrawableRes placeHolderDrawable: Int,
        @DrawableRes errorDrawable: Int,
        loadOnlyFromCache: Boolean = false,
        onLoadingFinished: () -> Unit = {}
) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished.invoke()
            return false
        }

        override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished.invoke()
            return false
        }
    }
    val requestOptions = RequestOptions
            .placeholderOf(placeHolderDrawable)
            .error(errorDrawable)
            .dontTransform()
            .onlyRetrieveFromCache(loadOnlyFromCache)
    Glide.with(this)
            .load(url)
            .apply(requestOptions)
            .listener(listener)
            .into(this)
}
