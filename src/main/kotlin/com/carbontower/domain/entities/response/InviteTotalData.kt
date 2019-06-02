package com.carbontower.domain.entities.response

data class InviteTotalData(val idPlayer: Int,
                           val idChampionship: Int,
                           val nmPlayer: String,
                           val nmChampionship: String,
                           val nmGame: String,
                           val alreadyAnswered: Int,
                           val accepted: Int)