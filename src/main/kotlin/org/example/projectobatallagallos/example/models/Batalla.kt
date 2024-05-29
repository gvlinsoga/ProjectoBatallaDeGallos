package org.example.projectobatallagallos.example.models

import kotlinx.serialization.Serializable

@Serializable
    data class Batalla(
        val fecha: String,
        val participante1: String,
        val participante2: String,
        val palabrasUtilizadas: MutableList<String>,
        val ganador: String
    )
