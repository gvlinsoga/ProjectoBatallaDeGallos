package org.example.rakata.example.gui


import javafx.collections.ListChangeListener
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.MenuButton
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Stage
import org.example.rakata.example.models.Participante

class CharacterSelectionController {
    private var selectedPlayer1: Participante? = null
    private var selectedPlayer2: Participante? = null
    @FXML
    var avatarPlayer1: ImageView = ImageView()

    @FXML
    var avatarPlayer2: ImageView = ImageView()

    @FXML
    lateinit var iniciarBatallaBoton: Button
    private var nombre = ""
    var siguientePantalla = ""
    var siguienteTitulo = ""


    @FXML
    lateinit var menuButtonPlayer2: MenuButton
    @FXML
    lateinit var menuButtonPlayer1: MenuButton

    var listaPersona = mutableListOf<Participante>()

    companion object {
        lateinit var instance: CharacterSelectionController
    }
    init {
        instance = this
        GlobalData.participants.addListener(ListChangeListener<Participante> {
            updateMenuItemsPlayer()
        })
    }

    private fun updateMenuItemsPlayer() {
        menuButtonPlayer1.items.clear()
        menuButtonPlayer2.items.clear()

        GlobalData.participants.forEach { participant ->
            val menuItem1 = MenuItem(participant.nombre)
            menuItem1.setOnAction {
                selectedPlayer1 = participant
                changeAvatar(participant, 1)
            }
            menuButtonPlayer1.items.add(menuItem1)

            val menuItem2 = MenuItem(participant.nombre)
            menuItem2.setOnAction {
                selectedPlayer2 = participant
                changeAvatar(participant, 2)
            }
            menuButtonPlayer2.items.add(menuItem2)
        }
    }

    fun seleccionarGallo(): Boolean {
        val gallo = listaPersona.find { it.nombre == nombre }
        if (gallo != null) {
            GlobalData.participants.remove(gallo)
            return true
        }
        return false
    }

    fun initialize() {
        updateMenuItemsPlayer()
    }

    private fun changeAvatar(participant: Participante, player: Int) {
        val avatarPath = "/org/example/batalladegallos/images/${participant.urlFotoPerfil}"
        val image = Image(javaClass.getResource(avatarPath).toExternalForm())
        if (player == 1) {
            avatarPlayer1.image = image
        } else if (player == 2) {
            avatarPlayer2.image = image
        }
    }



    fun iniciarBatalla() {
        iniciarBatallaBoton.setOnAction {
            siguientePantalla = "/org/example/batalladegallos/gui/game-screen.fxml"
            siguienteTitulo = "Batalla de Gallos - Arena de Combate"
            val currentStage = iniciarBatallaBoton.scene.window as Stage
            val fxmlLoader = FXMLLoader(javaClass.getResource(siguientePantalla))
            val newScene = Scene(fxmlLoader.load())
            currentStage.title = siguienteTitulo
            currentStage.scene = newScene
            currentStage.show()

            val gameController = fxmlLoader.getController<GameController>()
            val player2 = selectedPlayer2 ?: Participante("Default Player", "defaultAvatar.png", 0, "")
            gameController.initialize(selectedPlayer1!!, player2)
        }
    }
}





/*  //HAY QUE CAMBIAR TAMBIÉN ESTO, ES SUPONIENDO QUE EL GALLO ESTÁ CREADO. MODIFICAAAR
  private var gallosDisponibles: MutableList<Participante> = mutableListOf(
      Participante("Gallo1", "", 0), Participante("Gallo2", "", 0)
  )
  private var gallosSeleccionados: MutableList<Participante> = mutableListOf()

  fun seleccionarGallo(): Boolean {
      val gallo = gallosDisponibles.find { it.nombre == nombre }
      if (gallo != null) {
          gallosSeleccionados.add(gallo)
          gallosDisponibles.remove(gallo)
          return true
      }
      return false
  }

  fun obtenerGallosDisponibles(): List<Participante> {
      return gallosDisponibles
  }

  fun obtenerGallosSeleccionados(): List<Participante> {
      return gallosSeleccionados
  }

  fun resetearSeleccion() {
      gallosDisponibles.addAll(gallosSeleccionados)
      gallosSeleccionados.clear()
  }

  fun hayGallosDisponibles(): Boolean {
      return gallosDisponibles.isNotEmpty()
  }

  fun iniciarBatalla() {
      iniciarBatallaBoton.setOnAction {
          println("Iniciando batalla con los siguientes gallos:")
          siguientePantalla = "/org/example/batalladegallos/gui/game-screen.fxml"
          siguienteTitulo = "Batalla de Gallos - Arena de Combate"
          val currentStage = iniciarBatallaBoton.scene.window as Stage
          val fxmlLoader = FXMLLoader(javaClass.getResource(siguientePantalla))
          val newScene = Scene(fxmlLoader.load())
          currentStage.title = siguienteTitulo
          currentStage.scene = newScene
          currentStage.show()

      }

  }

  fun main() {
      val scanner = Scanner(System.`in`)
      val controller = CharacterSelectionController()
      var continuar = true

      while (continuar && controller.hayGallosDisponibles()) {
          println("Gallos disponibles para seleccionar:")
          controller.obtenerGallosDisponibles().forEach {
              println("${it.nombre} - ${it.urlFotoPerfil}")
          }

          println("Ingrese el apodo del gallo a seleccionar o 'exit' para salir:")
          val input = scanner.nextLine()

          if (input == "exit") {
              println("Finalizando la selección de personajes.")
              continuar = false
          } else if (controller.seleccionarGallo()) {
              println("Gallo seleccionado con éxito.")
          } else {
              println("Gallo no disponible o ya seleccionado.")
          }
      }

      println("Selección finalizada. Gallos seleccionados:")
      controller.obtenerGallosSeleccionados().forEach {
          println("${it.nombre} - ${it.urlFotoPerfil}")
      }
  } */









