package com.example.model

data class DeviceInfoBox(
    val id : Int,
    val name : String,
    val maker : String,
    val os : String?,
    val displaySize: Float?,
    val dateModified : String,
    val dateAdded : String
)
