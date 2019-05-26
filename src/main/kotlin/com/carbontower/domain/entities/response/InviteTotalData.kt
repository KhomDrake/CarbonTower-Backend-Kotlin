package com.carbontower.domain.entities.response

data class InviteTotalData(val idPlayer: Int,
                           val idChampionship: Int,
                           val alreadyAnswered: Int,
                           val accepted: Int)