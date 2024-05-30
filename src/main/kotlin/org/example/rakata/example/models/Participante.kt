package org.example.rakata.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Participante(val nombre: String, val urlFotoPerfil: String, var puntuacio : Int, var dataNaixement : String)