package com.example.spaceexplorer.models

import kotlinx.serialization.Serializable

@Serializable
data class Planet (val name: String, val type: String, val distance: Double)