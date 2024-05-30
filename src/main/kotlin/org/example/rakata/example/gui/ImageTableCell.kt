package org.example.rakata.example.gui

import javafx.scene.control.ContentDisplay
import javafx.scene.control.TableCell
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.example.rakata.example.models.Participante

class ImageTableCell : TableCell<Participante, String>() {
    private val imageView = ImageView()

    init {
        imageView.fitHeight = 50.0
        imageView.fitWidth = 50.0
        contentDisplay = ContentDisplay.GRAPHIC_ONLY
    }

    override fun updateItem(item: String?, empty: Boolean) {
        super.updateItem(item, empty)
        if (item == null || empty) {
            graphic = null
        } else {
            imageView.image = Image(item)
            graphic = imageView
        }
    }
}