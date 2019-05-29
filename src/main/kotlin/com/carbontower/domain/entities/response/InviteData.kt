package com.carbontower.domain.entities.response

data class InviteData(val idPlayer: Int,
                      val idChampionship: Int,
                      val nmChampionship: String,
                      val idOwner: Int,
                      val nmGame: String,
                      val alreadyAnswered: Int,
                      val accepted: Int)