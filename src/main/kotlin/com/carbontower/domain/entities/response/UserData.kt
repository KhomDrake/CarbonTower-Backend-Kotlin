package com.carbontower.domain.entities.response

data class UserData(val idUser: String,
                    val username: String,
                    val roles: List<Int>,
                    val idUserRole: Int)