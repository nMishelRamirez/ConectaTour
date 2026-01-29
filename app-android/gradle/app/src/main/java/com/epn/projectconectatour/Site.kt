package com.epn.projectconectatour

import java.io.Serializable

data class Site(
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String = "General"
) : Serializable
