package org.example.projectobatallagallos.example.gui

import org.example.projectobatallagallos.example.models.Participante


class GameplayController {
    private var palabrasSeleccionadas: Map<String, List<String>> = emptyMap()
    var gallos: List<Participante> = listOf()
    var temporizador = Temporizador()

    fun iniciarJuego(filePath: String) {
        val palabras = leerArchivo(filePath)
        palabrasSeleccionadas = agruparPalabrasRimadas(palabras)
        val seleccionadas = seleccionarPalabrasParaJuego(palabrasSeleccionadas)


        gallos.forEach {
            println("${it.apodo}: ${it.palabras.joinToString(", ")}")
        }

        temporizador.iniciar()
    }

    private fun leerArchivo(filePath: String): Set<String> {
        val file = File(filePath)
        val palabras = mutableSetOf<String>()
        val pattern = Pattern.compile("\\b[\\p{L}&&[^\\p{P}]]{4,}\\b")

        if (file.exists()) {
            file.readLines().forEach { linea ->
                val matcher = pattern.matcher(linea.toLowerCase())
                while (matcher.find()) {
                    val palabra = matcher.group()
                    if (!palabra.any { it.isDigit() }) {
                        palabras.add(palabra)
                    }
                }
            }
        } else {
            println("El archivo no existe")
        }

        return palabras
    }

    private fun agruparPalabrasRimadas(palabras: Set<String>): Map<String, List<String>> {
    val gruposDeRimas = mutableMapOf<String, MutableList<String>>()

    palabras.forEach { palabra ->
        val rima = extraerUltimaSilaba(palabra)
        if (rima.isNotEmpty()) {
            gruposDeRimas.getOrPut(rima) { mutableListOf() }.add(palabra)
        }
    }

    return gruposDeRimas.filter { it.value.size >= 20 }
            .map { Palabras(it.key, it.value) }
}


    private fun seleccionarPalabrasParaJuego(gruposDeRimas: Map<String, List<String>>): List<Palabras> {
    return gruposDeRimas.shuffled().take(2).map { grupo ->
        Palabras(grupo.rima, grupo.palabrasDisponibles.shuffled().take((19..20).random()).toMutableList())
    }
}

    private fun extraerUltimaSilaba(palabra: String): String {
        val regex = "([aeiouAEIOU]+[^aeiouAEIOU]*$)".toRegex()
        return regex.find(palabra)?.value ?: ""
    }
}

class Temporizador {
    private var timer: Timer? = null
    fun iniciar() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            var segundos = 60 // Duración de un minuto

            override fun run() {
                if (segundos > 0) {
                    println("Tiempo restante: $segundos segundos")
                    segundos--
                } else {
                    println("Tiempo finalizado.")
                    cancelar()
                }
            }
        }, 0, 1000)
    }
    fun cancelar() {
        timer?.cancel()
        println("Temporizador detenido.")
    }
}

fun main() {
    val controller = GameplayController()
    controller.iniciarJuego("src/main/kotlin/org/example/BatallaDeGallos/Persistence/TirantLoBlanc_Caps1_99.txt")
}


