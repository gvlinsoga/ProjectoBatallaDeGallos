package org.example.projectobatallagallos.example.gui

import org.example.projectobatallagallos.example.models.Participante
import javafx.fxml.FXML
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.util.Callback

class RankingController {
    @FXML
    private lateinit var listViewRanking: ListView<Participante>

    private val ranking: List<Participante> = listOf()

    @FXML
    fun initialize() {
        configurarListView()
        cargarRanking()
    }

    private fun configurarListView() {
        listViewRanking.cellFactory = Callback<ListView<Participante>, ListCell<Participante>> {
            object : ListCell<Participante>() {
                private val imageView = ImageView()
                override fun updateItem(gallo: Participante?, empty: Boolean) {
                    super.updateItem(gallo, empty)
                    if (empty || gallo == null) {
                        text = null
                        graphic = null
                    } else {
                        imageView.image = Image(gallo.urlFotoPerfil, 50.0, 50.0, true, true)
                        text = "${gallo.nombre} - ${gallo.puntuacio}"
                        graphic = imageView
                    }
                }
            }
        }
    }

    private fun cargarRanking() {
        val rankingOrdenado = ranking.sortedByDescending { it.puntuacio }
        listViewRanking.items.clear()
        listViewRanking.items.addAll(rankingOrdenado as Collection<Participante>)
    }
}

