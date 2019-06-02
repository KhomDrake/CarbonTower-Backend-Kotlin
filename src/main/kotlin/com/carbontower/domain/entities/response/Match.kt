package com.carbontower.domain.entities.response

data class Match(val idMatch: Int, var times: MutableList<Time>, var winner: Time, val date: String, val time: String)