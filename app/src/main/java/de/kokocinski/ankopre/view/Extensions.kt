package de.kokocinski.ankopre.view

import android.content.Context
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(context)
            .load(url)
            .crossFade()
            .centerCrop()
            .into(this)
}

fun View.snackBar(text: CharSequence, duration: Int = Snackbar.LENGTH_LONG, init: Snackbar.() -> Unit = {}) {
    val snackBar = Snackbar.make(this, text, duration)
    snackBar.init()
    snackBar.show()
}

fun Snackbar.action(label: CharSequence, color: Int? = null, action: () -> Unit) {
    setAction(label) { action() }
    color?.apply { setActionTextColor(color) }
}

val Context.connectivityManager: ConnectivityManager
    get() {
        return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
