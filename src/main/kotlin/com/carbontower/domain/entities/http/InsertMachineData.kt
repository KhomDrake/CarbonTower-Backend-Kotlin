package com.carbontower.domain.entities.http

data class InsertMachineData(
    val idMachine: String,
    val motherBoard: String,
    val os: String,
    val manufacturer: String,
    val model: String
)