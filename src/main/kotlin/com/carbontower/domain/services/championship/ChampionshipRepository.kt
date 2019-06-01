package com.carbontower.domain.services.championship

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.database.*
import com.carbontower.domain.entities.http.SingupChampionshipData
import com.carbontower.domain.entities.response.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


class ChampionshipRepository : IChampionshipRepository {
    override fun insertAdministratorInChampionship(idUserAdministrator: Int, idChampionship: Int) {
       transaction {
           T_ADMINISTRATOR_CHAMPIONSHIP.insert {
               it[T_ADMINISTRATOR_CHAMPIONSHIP.idAdministrator_fk] = idUserAdministrator
               it[T_ADMINISTRATOR_CHAMPIONSHIP.idChampionship_fk] = idChampionship
           }
       }
    }

    override fun insertRoleAdministrator(idAdministrator: String) {
        transaction {
            T_USER_ROLE.insert {
                it[T_USER_ROLE.idUser_fk] = idAdministrator
                it[T_USER_ROLE.idRole_fk] = 2
            }
        }
    }

    override fun getAllInvitesChampionship(idChampionship: Int): List<InviteTotalData> {
        var invites = mutableListOf<InviteTotalData>()

        transaction {
            val invitesDb = T_INVITE_PLAYER.select { T_INVITE_PLAYER.idChampionship_fk.eq(idChampionship) }

            invitesDb.forEach {
                invites.add(InviteTotalData(it[T_INVITE_PLAYER.idPlayer_fk],
                    idChampionship,
                    it[T_INVITE_PLAYER.alreadyAnswered],
                    it[T_INVITE_PLAYER.accepted]))
            }
        }

        return invites.toList()
    }

    override fun existChampionship(idChampionship: Int): Boolean {
        var exist = false
        transaction {
            exist = T_CHAMPIONSHIP.select { T_CHAMPIONSHIP.idChampionship.eq(idChampionship) }.count() != 0
        }
        return exist
    }

    override fun getGames(): List<GameData> {
        val games = mutableListOf<GameData>()

        transaction {
            val gamesDb = T_GAME.selectAll()
            gamesDb.forEach { games.add(GameData(it[T_GAME.idGame], it[T_GAME.nmGame])) }
        }

        return games.toList()
    }

    override fun alreadyExistInvite(idPlayer: Int, idChampionship: Int): Boolean {
        var exist = false

        transaction {
            exist = T_INVITE_PLAYER.select {
                T_INVITE_PLAYER.idPlayer_fk.eq(idPlayer)
                    .and(T_INVITE_PLAYER.idChampionship_fk.eq(idChampionship))
            }.count() != 0
        }

        return exist
    }

    override fun createInvite(idPlayer: Int, idChampionship: Int) {
        transaction {
            T_INVITE_PLAYER.insert {
                it[T_INVITE_PLAYER.idPlayer_fk] = idPlayer
                it[T_INVITE_PLAYER.idChampionship_fk] = idChampionship
                it[T_INVITE_PLAYER.accepted] = 0
                it[T_INVITE_PLAYER.alreadyAnswered] = 0
            }
        }
    }

    override fun getPlayersChampionship(idUserRole: Int, idChampionship: Int): List<PlayerChampionshipData> {
        val list = mutableListOf<PlayerChampionshipData>()

        transaction {
            val players = (T_USER innerJoin T_USER_ROLE innerJoin T_CHAMPIONSHIP innerJoin T_PLAYER_IN_CHAMPIONSHIP)
                .select {
                    T_CHAMPIONSHIP.owner_fk.eq(idUserRole)
                        .and(T_USER.idUser.eq(T_USER_ROLE.idUser_fk))
                        .and(T_PLAYER_IN_CHAMPIONSHIP.idChampionship_fk.eq(idChampionship))
                        .and(T_CHAMPIONSHIP.idChampionship.eq(idChampionship))
                        .and(T_USER_ROLE.idUserRole.eq(T_PLAYER_IN_CHAMPIONSHIP.idPlayer_fk))
                }

            players.forEach {
                list.add(PlayerChampionshipData(it[T_USER.nmUser],
                    it[T_USER_ROLE.idUserRole],
                    idChampionship
                    ))
            }
        }

        return list.toList()
    }

    override fun getChampionship(idUser: String, idUserRole: Int, idChampionship: Int): ChampionshipData {
        var nmChampionship = ""
        var nmGame = ""
        var nmUser = ""

        transaction {
            val championships = (T_USER innerJoin T_USER_ROLE innerJoin T_ROLE innerJoin T_CHAMPIONSHIP innerJoin T_GAME)
                .select { T_USER.idUser.eq(idUser)
                    .and(T_USER_ROLE.idUserRole.eq(idUserRole))
                    .and(T_ROLE.idRole.eq(Role.Empresa.ordinal)
                        .and(T_CHAMPIONSHIP.owner_fk.eq(idUserRole)
                            .and(T_CHAMPIONSHIP.idChampionship.eq(idChampionship))
                            .and(T_CHAMPIONSHIP.idGame_fk.eq(T_GAME.idGame))))
                }

            championships.forEach {
                nmChampionship = it[T_CHAMPIONSHIP.nmChampionship]
                nmGame = it[T_GAME.nmGame]
                nmUser = it[T_USER.nmUser]
            }
        }

        return ChampionshipData(nmChampionship, nmGame, nmUser, idChampionship)
    }

    override fun getChampionships(idUserRole: Int): List<ChampionshipData> {
        val list = mutableListOf<ChampionshipData>()
        transaction {
            val championships = (T_USER innerJoin T_USER_ROLE innerJoin T_ROLE innerJoin T_CHAMPIONSHIP innerJoin T_GAME)
                .select { T_USER.idUser.eq(T_USER_ROLE.idUser_fk)
                            .and(T_USER_ROLE.idUserRole.eq(idUserRole))
                            .and(T_ROLE.idRole.eq(Role.Empresa.ordinal)
                            .and(T_CHAMPIONSHIP.owner_fk.eq(idUserRole)
                            .and(T_CHAMPIONSHIP.idGame_fk.eq(T_GAME.idGame))))
                }

            championships.forEach {
                list.add(ChampionshipData(it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_GAME.nmGame],
                    it[T_USER.nmUser],
                    it[T_CHAMPIONSHIP.idChampionship]))
            }
        }
        return list.toList()
    }

    override fun insertchampionship(idUserRole: Int, signupChampionshipData: SingupChampionshipData) {
        transaction {
            T_CHAMPIONSHIP.insert {
                it[T_CHAMPIONSHIP.idGame_fk] = signupChampionshipData.idGame
                it[T_CHAMPIONSHIP.nmChampionship] = signupChampionshipData.nameChampionship
                it[T_CHAMPIONSHIP.owner_fk] = idUserRole
            }
        }
    }

    /*
        Dota 2
        Fifa 19
        Fortnite
        Hearthstone
        League of Legends
        Overwatch
        PUBG
        Rainbow Six Siege
        Street Fighter V
        Startcraft 2
     */

    override fun getIdUserRole(idUser: String, empresa: Role): Int {
        var idRole = 0

        transaction {
            val results = com.carbontower.domain.entities.database.T_USER_ROLE.select { T_USER_ROLE.idUser_fk.eq(idUser).and(
                T_USER_ROLE.idRole_fk.eq(empresa.ordinal)) }
            results.forEach {
                idRole = it[T_USER_ROLE.idUserRole]
            }
        }
        return idRole
    }
}