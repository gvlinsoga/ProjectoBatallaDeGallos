package org.example.rakata.example.gui

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.util.Duration
import org.example.rakata.example.models.Palabras
import org.example.rakata.example.models.Participante
import kotlin.random.Random

class GameController {

    @FXML
    lateinit var timerPlayer1: ProgressBar
    @FXML
    lateinit var timerPlayer2: ProgressBar
    @FXML
    var roundCounter: Text  = Text()
    @FXML
    lateinit var player1Name: Text
    @FXML
    lateinit var player2Name: Text
    @FXML
    lateinit var goRankingButton: Button
    @FXML
    var aplaudimetroProgress: ProgressBar = ProgressBar()
    @FXML
    var aplausosContador: Label = Label()
    @FXML
    var rondaCounter: Label = Label()
    @FXML
    var tiempoPlayer1 : Label = Label()
    @FXML
    var tiempoPlayer2 : Label = Label()
    @FXML
    var scorePlayer1 : Label = Label()
    @FXML
    var scorePlayer2 : Label = Label()
    @FXML
    private lateinit var menuPalabrasPlayer1: MenuButton
    @FXML
    private lateinit var menuPalabrasPlayer2: MenuButton
    @FXML
    lateinit var avatarPlayer1: javafx.scene.image.ImageView
    @FXML
    lateinit var avatarPlayer2: javafx.scene.image.ImageView

    private lateinit var player1: Participante
    private lateinit var player2: Participante


    private var currentRound = 0
    private var currentPlayer = 1
    var siguientePantalla = ""
    var siguienteTitulo = ""
    var primerCop = true

    private val palabrasPlayer1 = Palabras("Rima 1", mutableListOf("Palabra 1", "Palabra 2", "Palabra 3"))
    private val palabrasPlayer2 = Palabras("Rima 2", mutableListOf("Palabra 4", "Palabra 5", "Palabra 6"))

    fun initialize(player1Data: Participante, player2Data: Participante) {
        player1 = player1Data
        player2 = player2Data
        player1Name.text = player1.nombre
        player2Name.text = player2.nombre
        updateAvatar(player1.urlFotoPerfil, avatarPlayer1)
        updateAvatar(player2.urlFotoPerfil, avatarPlayer2)
        updateMenuItemsWords()
        startRound()

    }


    private fun updateAvatar(url: String, imageView: javafx.scene.image.ImageView) {
        val avatarPath = "/org/example/batalladegallos/images/$url"
        val image = Image(javaClass.getResource(avatarPath).toExternalForm())
        imageView.setImage(image)
    }
    private fun updateMenuItemsWords() {
        menuPalabrasPlayer1.items.clear()
        menuPalabrasPlayer2.items.clear()
        addWordsToMenu(palabrasPlayer1, menuPalabrasPlayer1)
        addWordsToMenu(palabrasPlayer2, menuPalabrasPlayer2)
    }

    private fun addWordsToMenu(palabras: Palabras, menu: MenuButton) {
        palabras.palabrasDisponibles.forEach { word ->
            val menuItem = MenuItem(word)
            menu.items.add(menuItem)
            menuItem.setOnAction {
                menuItem.isDisable = true
                menuItem.style = "-fx-text-fill: gray;"
                palabras.palabrasUsadas.add(word)
                palabras.palabrasDisponibles.remove(word)
            }
        }
    }

    private fun startRound() {
        currentRound++
        print("Starting round $currentRound")
        roundCounter.text = "Round: $currentRound"
        player1Name.text = player1.nombre
        player2Name.text = player2.nombre
        startTimer()
    }

    private fun startTimer() {
        println("Starting timer")
        val players = listOf(timerPlayer1, timerPlayer2)
        val labels = listOf(tiempoPlayer1, tiempoPlayer2)
        val scores = listOf(scorePlayer1, scorePlayer2)
        val timeline = Timeline(
            KeyFrame(Duration.seconds(1.0), EventHandler<ActionEvent> {
                val currentPlayerProgressBar = players[currentPlayer - 1]
                val currentPlayerLabel = labels[currentPlayer - 1]
                val currentPlayerScore = scores[currentPlayer - 1]
                updateProgressBar(currentPlayerProgressBar)
                if (currentPlayerProgressBar.progress <= 0) {
                    switchPlayer(currentPlayerProgressBar, currentPlayerScore, players, labels)
                }
                updateLabel(currentPlayerProgressBar, currentPlayerLabel)

            })
        )
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.play()
    }

    private fun updateProgressBar(progressBar: ProgressBar) {
        progressBar.progress -= 1.0 / 30
    }

    private fun switchPlayer(currentPlayerProgressBar: ProgressBar, currentPlayerScore: Label, players: List<ProgressBar>, labels: List<Label>) {
        currentPlayerProgressBar.style = "-fx-accent: gray;"
        currentPlayer = 3 - currentPlayer
        val nextPlayerProgressBar = players[currentPlayer - 1]
        val nextPlayerLabel = labels[currentPlayer - 1]
        nextPlayerProgressBar.progress = 1.0
        nextPlayerLabel.text = "${(nextPlayerProgressBar.progress * 30).toInt()} segundos restantes"
        if (currentPlayer == 1) {
            currentRound++
            rondaCounter.text = "Round: $currentRound"
            if (currentRound < 4) {
                startRound()
            } else {
                goRanking()
            }
        }
        aplaudimetroProgress()

    }

    private fun updateLabel(progressBar: ProgressBar, label: Label) {
        println(label)
        println(" dopfskopdsfkoprfdskopfdskdopsfkfdopsk ")
        val secondsLeft = (progressBar.progress * 30).toInt()
        if (secondsLeft  <= 0) {
            label.text = "Esperando Turno"
        } else {
            label.text = "$secondsLeft segundos"
        }

    }

    fun aplaudimetroProgress() {
        if (!primerCop) {
            val randomProgress = Random.nextDouble()
            aplaudimetroProgress.progress = randomProgress

            val score = (randomProgress * 100).toInt()

            if (currentPlayer != 1) {
                player1.puntuacion += score
                scorePlayer1.text = "Score: ${player1.puntuacion}"
            } else {
                player2.puntuacion += score
                scorePlayer2.text =  "Score: ${player2.puntuacion}"
            }
            aplausosContador.text = ("Aplausos: $score")
        } else {
            primerCop = false
        }
    }


    fun goRanking() {
        siguientePantalla = "/org/example/batalladegallos/gui/ranking-screen.fxml"
        siguienteTitulo = "Batalla de Gallos - Ranking"
        val stage = (goRankingButton.scene.window as Stage)
        val fxmlLoader = FXMLLoader(javaClass.getResource(siguientePantalla))
        val scene = Scene(fxmlLoader.load())
        stage.title = siguienteTitulo
        stage.scene = scene
        stage.show()
        val rankingController = fxmlLoader.getController<RankingController>()
        rankingController.initialize(player1, player2)
    }
}