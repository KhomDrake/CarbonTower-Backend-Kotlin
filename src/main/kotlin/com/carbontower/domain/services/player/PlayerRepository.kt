package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.database.*
import com.carbontower.domain.entities.response.ChampionshipData
import com.carbontower.domain.entities.response.InviteData
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class PlayerRepository : IPlayerRepository {
    override fun existChampionship(idChampionship: Int): Boolean {
        var exist = false

        transaction {
            exist = T_CHAMPIONSHIP.select { T_CHAMPIONSHIP.idChampionship.eq(idChampionship) }.count() != 0
        }

        return exist
    }

    override fun administerThisChampionship(idUserRole: Int, idChampionship: Int): Boolean {
        var administer = false

        transaction {
            administer = T_ADMINISTRATOR_CHAMPIONSHIP.select { T_ADMINISTRATOR_CHAMPIONSHIP.idChampionship_fk.eq(idChampionship)
                .and(T_ADMINISTRATOR_CHAMPIONSHIP.idAdministrator_fk.eq(idUserRole))}.count() != 0
        }

        return administer
    }

    override fun administratorChampionship(idUserRole: Int): List<ChampionshipData> {
        val championships = mutableListOf<ChampionshipData>()

        transaction {
            val championshipsDb = (T_ADMINISTRATOR_CHAMPIONSHIP innerJoin T_CHAMPIONSHIP)
                .select { T_ADMINISTRATOR_CHAMPIONSHIP.idAdministrator_fk.eq(idUserRole)
                    .and(T_ADMINISTRATOR_CHAMPIONSHIP.idChampionship_fk.eq(T_CHAMPIONSHIP.idChampionship)) }

            championshipsDb.forEach {
                championships.add(ChampionshipData(it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_GAME.nmGame], "", it[T_CHAMPIONSHIP.idChampionship]))
            }
        }

        return championships
    }

    override fun championshipsParticipate(idUserRole: Int): List<ChampionshipData> {
        val listChampionships = mutableListOf<ChampionshipData>()

        transaction {
            val championships = (T_USER_ROLE innerJoin T_CHAMPIONSHIP innerJoin T_PLAYER_IN_CHAMPIONSHIP innerJoin T_GAME)
                .select {
                    T_USER_ROLE.idUserRole.eq(idUserRole)
                        .and(T_PLAYER_IN_CHAMPIONSHIP.idPlayer_fk.eq(idUserRole))
                        .and(T_PLAYER_IN_CHAMPIONSHIP.idChampionship_fk.eq(T_CHAMPIONSHIP.idChampionship))
                        .and(T_CHAMPIONSHIP.idGame_fk.eq(T_GAME.idGame))
                }

            championships.forEach {
                listChampionships.add(ChampionshipData(it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_GAME.nmGame], "", it[T_CHAMPIONSHIP.idChampionship]))
            }
        }

        return listChampionships.toList()
    }

    override fun acceptInvite(idUserRole: Int, idChampionship: Int) {
        transaction {
            T_INVITE_PLAYER.update({ T_INVITE_PLAYER.idChampionship_fk.eq(idChampionship)
                .and(T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole))
                .and(T_INVITE_PLAYER.alreadyAnswered.eq(0))}) {
                it[T_INVITE_PLAYER.accepted] = 1
                it[T_INVITE_PLAYER.alreadyAnswered] = 1
            }

            T_PLAYER_IN_CHAMPIONSHIP.insert {
                it[T_PLAYER_IN_CHAMPIONSHIP.idChampionship_fk] = idChampionship
                it[T_PLAYER_IN_CHAMPIONSHIP.idPlayer_fk] = idUserRole
            }
        }
    }

    override fun refuseInvite(idUserRole: Int, idChampionship: Int) {
        transaction {
            T_INVITE_PLAYER.update({ T_INVITE_PLAYER.idChampionship_fk.eq(idChampionship)
                .and(T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole))
                .and(T_INVITE_PLAYER.alreadyAnswered.eq(0))}) {
                it[T_INVITE_PLAYER.accepted] = 0
                it[T_INVITE_PLAYER.alreadyAnswered] = 1
            }
        }
    }

    override fun getIdUserRole(idUserRole: String, jogador: Role): Int {
        var idRole = 0

        transaction {
            val results = T_USER_ROLE.select { T_USER_ROLE.idUser_fk.eq(idUserRole).and(T_USER_ROLE.idRole_fk.eq(jogador.ordinal)) }
            results.forEach {
                idRole = it[T_USER_ROLE.idUserRole]
            }
        }
        return idRole
    }

    override fun getInvites(idUserRole: Int): MutableList<InviteData> {
        val list = mutableListOf<InviteData>()

        transaction {
            val invites = (T_INVITE_PLAYER innerJoin T_CHAMPIONSHIP innerJoin T_GAME).select {
                T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole).and(T_INVITE_PLAYER.idChampionship_fk.eq(T_CHAMPIONSHIP.idChampionship))
                    .and(T_INVITE_PLAYER.alreadyAnswered.eq(0)).and(T_GAME.idGame.eq(T_CHAMPIONSHIP.idGame_fk))
            }

            invites.forEach {
                list.add(InviteData(it[T_INVITE_PLAYER.idPlayer_fk],
                    it[T_CHAMPIONSHIP.idChampionship],
                    it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_CHAMPIONSHIP.owner_fk],
                    it[T_GAME.nmGame]))
            }
        }

        return list
    }
}