package com.carbontower.domain.entities.response

data class Match(val times: List<Time>, val winner: Time, val date: String, val time: String)