package org.example.rakata.example.models

import kotlinx.serialization.Serializable

data class Palabras(val rima: String, val palabrasDisponibles: MutableList<String>) {
    val palabrasUsadas = mutableListOf<String>()
}
