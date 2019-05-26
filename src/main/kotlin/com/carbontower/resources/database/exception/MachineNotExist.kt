package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class MachineNotExist(private val idMachine: Int) : NotAcceptable() {
    override var message: String = "Não foi possível localizar a máquina"
    override var messageLog: String = "Máquina com id: $idMachine, não existe."
}