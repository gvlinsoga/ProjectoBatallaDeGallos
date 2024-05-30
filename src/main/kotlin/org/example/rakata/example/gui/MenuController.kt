package org.example.rakata.example.gui

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.util.Callback
import org.example.rakata.example.models.Participante
import java.io.File
import java.io.IOException
import java.nio.file.Paths
import java.util.*

class MenuController {
    @FXML
    var loreBoton: Button = Button()
    lateinit var jugarBoton: Button
    lateinit var nuevoGalloBoton: Button
    lateinit var guardarPersonajeBoton: Button
    var rankingBoton: Button = Button()
    var salirBoton: Button = Button()
    var siguientePantalla = ""
    var siguienteTitulo = ""



    fun mainMenu() {

        try {
            loreBoton.setOnAction {
                explorarArchivos()
            }
            nuevoGalloBoton.setOnAction {
                crearNuevoPersonaje()
                cambiarAvatar()
            }
            jugarBoton.setOnAction {
                siguientePantalla()
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

    @FXML
    @Throws(IOException::class)
    fun siguientePantalla() {
        siguientePantalla = "/org/example/batalladegallos/gui/character-screen.fxml"
        siguienteTitulo = "Batalla de Gallos - Selección de Personajes"
        val currentStage = jugarBoton.scene.window as Stage
        val fxmlLoader = FXMLLoader(javaClass.getResource(siguientePantalla))
        val scene = Scene(fxmlLoader.load())
        currentStage.title = siguienteTitulo
        currentStage.scene = scene
        currentStage.show()
    }

    @Throws(IOException::class)
    fun crearNuevoPersonaje() {
        val dialog = Dialog<Participante>()
        dialog.title = "Nuevo Personaje"
        val fxmlLoader = FXMLLoader(javaClass.getResource("newcharacter-popupv2.fxml"))
        val dialogPane = fxmlLoader.load<DialogPane>()
        dialog.dialogPane = dialogPane

        dialog.dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)

        val controller = fxmlLoader.getController<MenuController>()
        dialog.resultConverter = Callback<ButtonType, Participante> { buttonType ->
            if (buttonType == ButtonType.OK) {
                controller.guardarFecha()
                controller.guardarGallo()
                Participante(controller.userName.text, controller.cumple, 0, "")
            } else {
                null
            }
        }
        val result = dialog.showAndWait()
        result.ifPresent { participant ->
            participants.add(participant)
        }
    }

    @FXML
    private var userAvatar: ImageView = ImageView()

    @FXML
    private var nombreAvatar: Label = Label()

    private val imageNames = listOf("mittens.png", "mrGrump.png", "angryCat.png", "scaredy.png", "catspurrov.png")
    private val labels = listOf("Mittens", "Mr Grump", "Angry Cat", "Scaredy", "Catspurrov")
    private var currentAvatarIndex = 0
    private var cumple = ""

    @Throws(IOException::class)
    @FXML
    fun cambiarAvatar() {
        currentAvatarIndex = (currentAvatarIndex + 1) % imageNames.size
        val imageName = imageNames[currentAvatarIndex]
        val label = labels[currentAvatarIndex]
        val image =
            (Image(Paths.get("src/main/resources/org/example/batalladegallos/images/${imageName}").toUri().toString()))
        userAvatar.image = image
        nombreAvatar.text = label
    }


    @FXML
    var userName = TextField()

    @FXML
    var cumField = DatePicker()
    private val participants = mutableListOf<Participante>()

    fun guardarFecha() {
        val date = cumField.value
        println("Date from DatePicker: $date")
        cumple = date.toString()
        println("Saved date: $cumple")
    }

    fun guardarGallo() {
        val nombre = userName.text
        val avatar = imageNames[currentAvatarIndex]
        GlobalData.participants.add(Participante(nombre, avatar, 0, ""))
        println("Nombre: $nombre, Cumpleaños: $cumple")
        println("participantes: ${GlobalData.participants}")
    }



    fun salir() {
        Platform.exit()
    }

}
