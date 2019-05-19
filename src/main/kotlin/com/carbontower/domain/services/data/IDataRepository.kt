package com.carbontower.domain.services.data

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.response.UserData

interface IDataRepository {
    fun getIdRole(idUser: String, role: Role): Int
    fun getUserData(idUser: String): UserData
}