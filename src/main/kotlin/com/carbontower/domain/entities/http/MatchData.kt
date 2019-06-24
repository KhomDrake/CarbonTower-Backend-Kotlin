package com.carbontower.domain.entities.http

data class MatchData(val date: String, val time: String, val winner: Int, val idChampionship: Int, val idsTeams: List<Int>)