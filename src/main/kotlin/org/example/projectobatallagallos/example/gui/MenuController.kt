package org.example.projectobatallagallos.example.gui

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.IOException
import java.nio.file.Paths

class MenuController {
    @FXML
    var loreBoton: Button = Button()
    lateinit var jugarBoton: Button
    lateinit var nuevoGalloBoton: Button
    var rankingBoton: Button = Button()
    var salirBoton: Button = Button()
    var initNoseqe = ""




    fun mainMenu() {

        try {
            loreBoton.setOnAction {
                explorarArchivos()
            }
            nuevoGalloBoton.setOnAction {
                openNewCharacterDialog()
                cambiarAvatar()

            }
            jugarBoton.setOnAction {
                siguientePantalla()

            }
            rankingBoton.setOnAction {
                verRanking()
            }
            salirBoton.setOnAction {
                salir()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }






    fun explorarArchivos() {
        val fileChooser = FileChooser()
        fileChooser.initialDirectory = File(System.getProperty("user.home"))
        val extFilter = FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
        fileChooser.extensionFilters.add(extFilter)
        val file = fileChooser.showOpenDialog(null)
        if (file != null) {
            println(file.absolutePath)
            loreBoton.text = "Lore: ${file.name}"
        }
    }

    @Throws(IOException::class)
    fun siguientePantalla() {
        val currentStage = jugarBoton.scene.window as Stage
        val fxmlLoader = FXMLLoader(javaClass.getResource("character-selection.fxml"))
        val scene = Scene(fxmlLoader.load())
        currentStage.title = "Batalla de Gallos - Selección de Personajes"
        currentStage.scene = scene
        currentStage.show()
    }

    @Throws(IOException::class)
    fun openNewCharacterDialog() {
        val dialog = javafx.scene.control.Dialog<ButtonType>()
        dialog.title = "Nuevo Personaje"
        val fxmlLoader = FXMLLoader(javaClass.getResource("newcharacter-popupv2.fxml"))
        val dialogPane = fxmlLoader.load<DialogPane>()
        dialog.dialogPane = dialogPane
        dialog.dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)
        val result = dialog.showAndWait()
        result.ifPresent { buttonType ->
            if (buttonType == ButtonType.OK) {
                println("OK button clicked")
                // Handle saving the new character
            }
        }
    }

    @FXML
    private lateinit var logo: ImageView

    @FXML
    private var userAvatar: ImageView = ImageView()

    @FXML
    private var nombreAvatar: Label = Label()

    // Listas de nombres de imágenes y etiquetas correspondientes
    private val imageNames = listOf("mrGrump.png", "mittens.png", "angryCat.png", "scaredy.png", "catspurrov.png")
    private val labels = listOf("Mr Grump", "Mittens", "Angry Cat", "Scaredy", "Catspurrov")
    private var currentAvatarIndex = 0

    @Throws(IOException::class)
@FXML
fun cambiarAvatar() {
    currentAvatarIndex = (currentAvatarIndex + 1) % imageNames.size

    val imageName = imageNames[currentAvatarIndex]
     val label = labels[currentAvatarIndex]
    val image = (Image(Paths.get("src/main/resources/org/example/batalladegallos/images/${imageName}").toUri().toString()))
        userAvatar.image = image
        nombreAvatar.text = label
}


@FXML
var userName = TextField()
    var cumField = DatePicker()

fun guardarGallo() {
    val nombre = userName.text
    val cumple = cumField.value
    println("Nombre: $nombre, Cumpleaños: $cumple")
    // Guardar el nuevo gallo
    // Cerrar la ventana
    // Mostrar mensaje de éxito
    // Limpiar los campos
}


    @FXML
    fun verRanking() {
        try {
            val stage = (rankingBoton.scene.window as Stage)  // Obtiene el Stage actual desde el botón
            val fxmlLoader = FXMLLoader(javaClass.getResource("/org/example/batalladegallos/gui/ranking-screen.fxml"))
            val scene = Scene(fxmlLoader.load())
            stage.title = "Batalla de Gallos - Ranking"
            stage.scene = scene
            stage.show()

            // Opcional: Si necesitas interactuar con el controlador de la pantalla de ranking
            val rankingController = fxmlLoader.getController<RankingController>()
            // Aquí podrías llamar a algún método de `rankingController` si es necesario
        } catch (e: IOException) {
            e.printStackTrace()  // Maneja la excepción en caso de error
        }
    }


fun salir() {
    Platform.exit()
}

}

