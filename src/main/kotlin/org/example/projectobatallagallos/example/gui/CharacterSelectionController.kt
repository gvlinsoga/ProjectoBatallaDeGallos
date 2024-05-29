package org.example.projectobatallagallos.example.gui

class CharacterSelectionController {


}


/*

import org.example.batalladegallos.Model.Gallo
import java.util.*

class CharacterSelectionController {

    //HAY QUE CAMBIAR TAMBIÉN ESTO, ES SUPONIENDO QUE EL GALLO ESTÁ CREADO. MODIFICAAAR
    private var gallosDisponibles: MutableList<Gallo> = mutableListOf(
        Gallo("Gallo1", 0, "url1.jpg"),
        Gallo("Gallo2", 0, "url2.jpg"),
        Gallo("Gallo3", 0, "url3.jpg")
    )
    private var gallosSeleccionados: MutableList<Gallo> = mutableListOf()

    fun seleccionarGallo(apodo: String): Boolean {
        val gallo = gallosDisponibles.find { it.apodo == apodo }
        if (gallo != null) {
            gallosSeleccionados.add(gallo)
            gallosDisponibles.remove(gallo)
            return true
        }
        return false
    }

    class CharacterSelectionController {

        //HAY QUE CAMBIAR TAMBIÉN ESTO, ES SUPONIENDO QUE EL GALLO ESTÁ CREADO. MODIFICAAAR
        private var gallosDisponibles: MutableList<Gallo> = mutableListOf(
            Gallo("Gallo1", 0, "url1.jpg"),
            Gallo("Gallo2", 0, "url2.jpg"),
            Gallo("Gallo3", 0, "url3.jpg")
        )
        private var gallosSeleccionados: MutableList<Gallo> = mutableListOf()

        fun seleccionarGallo(apodo: String): Boolean {
            val gallo = gallosDisponibles.find { it.apodo == apodo }
            if (gallo != null) {
                gallosSeleccionados.add(gallo)
                gallosDisponibles.remove(gallo)
                return true
            }
            return false
        }

        fun obtenerGallosDisponibles(): List<Gallo> {
            return gallosDisponibles
        }

        fun obtenerGallosSeleccionados(): List<Gallo> {
            return gallosSeleccionados
        }

        fun resetearSeleccion() {
            gallosDisponibles.addAll(gallosSeleccionados)
            gallosSeleccionados.clear()
        }

        fun hayGallosDisponibles(): Boolean {
            return gallosDisponibles.isNotEmpty()
        }
    }



fun main() {
    val scanner = Scanner(System.`in`)
    val controller = CharacterSelectionController()
    var continuar = true

    while (continuar && controller.hayGallosDisponibles()) {
        println("Gallos disponibles para seleccionar:")
        controller.obtenerGallosDisponibles().forEach {
            println("${it.apodo} - ${it.urlFotoPerfil}")
        }

        println("Ingrese el apodo del gallo a seleccionar o 'exit' para salir:")
        val input = scanner.nextLine()

        if (input == "exit") {
            println("Finalizando la selección de personajes.")
            continuar = false
        } else if (controller.seleccionarGallo(input)) {
            println("Gallo seleccionado con éxito.")
        } else {
            println("Gallo no disponible o ya seleccionado.")
        }
    }

    println("Selección finalizada. Gallos seleccionados:")
    controller.obtenerGallosSeleccionados().forEach {
        println("${it.apodo} - ${it.urlFotoPerfil}")
    }
}

 */