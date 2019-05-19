package com.carbontower.domain.services.data

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.response.UserData

class DataService(private val dataRepository: IDataRepository) {
    fun getIdRole(idUser: String, role: Role) = dataRepository.getIdRole(idUser, role)
    fun getUserData(idUser: String): UserData = dataRepository.getUserData(idUser)

}