package org.example.rakata.example.gui

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.example.rakata.example.models.Participante

object GlobalData {
    val participants: ObservableList<Participante> = FXCollections.observableArrayList()
}