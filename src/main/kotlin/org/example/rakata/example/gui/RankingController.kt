package org.example.rakata.example.gui

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.PerspectiveCamera
import javafx.scene.control.Button
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Stage
import javafx.util.Callback
import org.example.rakata.example.models.Participante


class RankingController {
    @FXML

    lateinit var tableView: TableView<Participante>


    @FXML
    lateinit var salirBoton: Button

    fun initialize() {
        // configurarListView()
        //  cargarRanking()
    }


    fun initialize(player1Data: Participante, player2Data: Participante) {
        val data = FXCollections.observableArrayList<Participante>()
        data.add(player1Data)
        data.add(player2Data)
        tableView.items = data

        val nameColumn = tableView.columns.find { it.text == "Name" } as TableColumn<Participante, String>
        nameColumn.setCellValueFactory { SimpleStringProperty(it.value.nombre) }

        val avatarColumn = tableView.columns.find { it.text == "Avatar" } as TableColumn<Participante, String>
        avatarColumn.setCellValueFactory { SimpleStringProperty(it.value.urlFotoPerfil) }
        avatarColumn.setCellFactory { ImageTableCell() }

        val scoreColumn = tableView.columns.find { it.text == "Score" } as TableColumn<Participante, Int>
        scoreColumn.setCellValueFactory { SimpleIntegerProperty(it.value.puntuacion).asObject() }
    }


    /*
        private fun configurarListView() {
            listViewRanking.cellFactory = Callback<ListView<Gallo>, ListCell<Gallo>> {
                object : ListCell<Gallo>() {
                    private val imageView = ImageView()
                    override fun updateItem(gallo: Gallo?, empty: Boolean) {
                        super.updateItem(gallo, empty)
                        if (empty || gallo == null) {
                            text = null
                            graphic = null
                        } else {
                            imageView.image = Image(gallo.urlFotoPerfil, 50.0, 50.0, true, true)
                            text = "${gallo.apodo} - ${gallo.puntuacion}"
                            graphic = imageView
                        }
                    }
                }
            }
        }

        private fun cargarRanking() {
            val rankingOrdenado = ranking.sortedByDescending { it.puntuacion }
            listViewRanking.items.clear()
            listViewRanking.items.addAll(rankingOrdenado as Collection<Gallo>)
        }
    */
    fun salirR() {
        salirBoton.setOnAction {
            val stage = salirBoton.scene.window as Stage
            stage.close()
        }
    }
}


