package com.qiaoyuang.kmmnews.androidApp

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import coil.load
import coil.request.CachePolicy
import coil.request.Disposable
import coil.request.ImageRequest
import coil.size.Scale
import kotlinx.coroutines.Dispatchers

/**
 * Coil 配置
 */

fun ImageRequest.Builder.getCoilDefaultConfig(): ImageRequest.Builder {
    dispatcher(Dispatchers.IO)
    allowHardware(true)
    allowRgb565(true)
    bitmapConfig(Bitmap.Config.RGB_565)
    memoryCachePolicy(CachePolicy.ENABLED)
    diskCachePolicy(CachePolicy.ENABLED)
    networkCachePolicy(CachePolicy.ENABLED)
    scale(Scale.FILL)
    return this
}

fun ImageView.loadURL(url: String, lifecycle: Lifecycle): Disposable = load(url) {
    crossfade(true)
    lifecycle(lifecycle)
    getCoilDefaultConfig()
}