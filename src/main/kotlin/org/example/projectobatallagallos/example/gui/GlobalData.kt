package org.example.projectobatallagallos.example.gui

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.example.projectobatallagallos.example.models.Participante

object GlobalData {
    val participants: ObservableList<Participante> = FXCollections.observableArrayList()
}