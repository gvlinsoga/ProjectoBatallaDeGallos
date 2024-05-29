package org.example.projectobatallagallos.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Palabras(val rima: String, val palabrasDisponibles: List<String>)
