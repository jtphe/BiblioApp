package com.example.biblioapp.models

class AppModel(
    val nom: String,
    val genre: String,
    val anneeSortie: Int,
    val imageUrl: String
) {
    override fun toString(): String {
        return "AppModel(nom='$nom', genre='$genre', anneeSortie=$anneeSortie)"
    }
}