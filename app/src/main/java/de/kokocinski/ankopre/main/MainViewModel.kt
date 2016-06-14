package de.kokocinski.ankopre.main

import android.databinding.ObservableField
import android.widget.ImageView
import de.kokocinski.ankopre.data.SampleData
import de.kokocinski.ankopre.view.loadImage
import javax.inject.Inject

class MainViewModel @Inject constructor() {

    val title = ObservableField<String>()
    val text = ObservableField<String>()

    private var imageView: ImageView? = null

    fun setImageView(imageView: ImageView) {
        this.imageView = imageView
    }

    fun setData(data: SampleData) {
        title.set(data.title)
        text.set(data.text)
        imageView?.loadImage(data.image)
    }

}