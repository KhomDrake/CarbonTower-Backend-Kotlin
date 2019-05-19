package com.carbontower.resources.database

import org.jetbrains.exposed.sql.Database

class Connection {

    fun createTables() {

    }

    fun dropTables() {

    }

    fun connectWithCarbonTowerDatabase() {
        connectWithSQLServer(
            server = "servercarbontower.database.windows.net",
            databaseName = "carbontower",
            user = "tower",
            password = "!Carbon6"
        )
    }

    private fun connectWithSQLServer(server: String, databaseName: String, user: String, password: String) {
        Database.connect(
            "jdbc:sqlserver://${server};databaseName=${databaseName}",
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            user = user,
            password = password
        )
    }
}