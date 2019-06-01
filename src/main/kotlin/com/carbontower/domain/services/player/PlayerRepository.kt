package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.database.*
import com.carbontower.domain.entities.http.MatchData
import com.carbontower.domain.entities.response.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class PlayerRepository : IPlayerRepository {
    override fun existTime(nmTime: String): Boolean {
        var exist = false
        transaction {
            exist = T_TEAM.select { T_TEAM.nmTeam.eq(nmTime) }.count() != 0
        }
        return exist
    }

    override fun existTime(idTime: Int): Boolean {
        var exist = false
        transaction {
            exist = T_TEAM.select { T_TEAM.idTeam.eq(idTime) }.count() != 0
        }
        return exist
    }

    override fun getIdTime(nmTime: String): Int {
        var idTime = 0
        transaction {
            val times = T_TEAM.select { T_TEAM.nmTeam.eq(nmTime) }
            times.forEach {
                idTime = it[T_TEAM.idTeam]
            }
        }
        return idTime
    }

    override fun insertTime(nmTime: String) {
        transaction {
            T_TEAM.insert {
                it[T_TEAM.nmTeam] = nmTime
            }
        }
    }

    override fun timeInChampionship(idTime: Int, idChampionship: Int): Boolean {
        var inChampionship = false

        transaction {
            inChampionship = T_TEAM_IN_CHAMPIONSHIP.select { T_TEAM_IN_CHAMPIONSHIP.idTeam_fk.eq(idTime)
                .and(T_TEAM_IN_CHAMPIONSHIP.idChampionship_fk.eq(idChampionship))
            }.count() != 0
        }

        return inChampionship
    }

    override fun insertTimeInChampionship(idTime: Int, idChampionship: Int) {
        transaction {
            T_TEAM_IN_CHAMPIONSHIP.insert {
                it[T_TEAM_IN_CHAMPIONSHIP.idChampionship_fk] = idChampionship
                it[T_TEAM_IN_CHAMPIONSHIP.idTeam_fk] = idTime
            }
        }
    }

    override fun playerInTime(idUserPlayer: Int, idTime: Int): Boolean {
        var inTime = false
        transaction {
            inTime = T_PLAYER_IN_TEAM.select { T_PLAYER_IN_TEAM.idPlayer_fk.eq(idUserPlayer)
                .and(T_PLAYER_IN_TEAM.idTeam_fk.eq(idTime))
            }.count() != 0
        }
        return inTime
    }

    override fun insertPlayerInTime(idUserPlayer: Int, idTime: Int) {
        transaction {
            T_PLAYER_IN_TEAM.insert {
                it[T_PLAYER_IN_TEAM.idPlayer_fk] = idUserPlayer
                it[T_PLAYER_IN_TEAM.idTeam_fk] = idTime
            }
        }
    }

    override fun insertMatch(matchData: MatchData) {
        transaction {
            T_MATCH.insert {
                it[T_MATCH.idChampionship_fk] = matchData.idChampionship
                it[T_MATCH.winner] = matchData.winner
                it[T_MATCH.date] = matchData.date
                it[T_MATCH.time] = matchData.time
            }
        }
    }

    override fun existMatch(idMatch: Int): Boolean {
        var exist = false

        transaction {
            exist = T_MATCH.select { T_MATCH.idMatch.eq(idMatch) }.count() != 0
        }

        return exist
    }

    override fun insertTimeInMatch(idMatch: Int, idTime: Int) {
        transaction {
            T_TEAM_IN_MATCH.insert {
                it[T_TEAM_IN_MATCH.idMatch_fk] = idMatch
                it[T_TEAM_IN_MATCH.idMatch_fk] = idTime
            }
        }
    }

    override fun getPlayerTimeInChampionship(idUserRole: Int, idChampionship: Int): Time {
        val players = mutableListOf<UserData>()
        var nmTime = ""

        transaction {
            val time = (T_PLAYER_IN_TEAM innerJoin T_TEAM innerJoin T_TEAM_IN_CHAMPIONSHIP).select {
                T_TEAM_IN_CHAMPIONSHIP.idChampionship_fk.eq(idChampionship)
                    .and(T_TEAM_IN_CHAMPIONSHIP.idTeam_fk.eq(T_TEAM.idTeam))
                    .and(T_PLAYER_IN_TEAM.idPlayer_fk.eq(idUserRole))
            }

            var idTime = 0

            time.forEach {
                idTime = it[T_TEAM.idTeam]
                nmTime = it[T_TEAM.nmTeam]
            }

            val playersInTime = (T_USER innerJoin T_USER_ROLE innerJoin T_PLAYER_IN_TEAM).select {
                T_PLAYER_IN_TEAM.idTeam_fk.eq(idTime)
                    .and(T_PLAYER_IN_TEAM.idPlayer_fk.eq(T_USER_ROLE.idUserRole))
                    .and(T_USER_ROLE.idUser_fk.eq(T_USER.idUser))
            }

            playersInTime.forEach {
                val userData = UserData(it[T_USER_ROLE.idUser_fk], it[T_USER.nmUser], listOf(), it[T_USER_ROLE.idUserRole])
                players.add(userData)
            }
        }

        return Time(nmTime = nmTime, players = players)
    }

    override fun getMatchsChampionship(idUserRole: Int, idChampionship: Int): List<Match> {
        val matchs = mutableListOf<Match>()

        transaction {
            val matchsDb = (T_MATCH)
        }

        return matchs.toList()
    }

    override fun getAllMatchsPlayer(idUserRole: Int): List<Match> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllTimesPlayers(idUserRole: Int): List<Time> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllMatchsPlayerChampionship(idUserRole: Int, idChampionship: Int): List<Match> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllTimesPlayerChampionship(idUserRole: Int, idChampionship: Int): List<Time> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInviteAlreadyAnswered(idUserRole: Int, idChampionship: Int): Boolean {
        var answer = false

        transaction {
            answer = T_INVITE_PLAYER.select { T_INVITE_PLAYER.idChampionship_fk.eq(idChampionship)
                .and(T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole))
                .and(T_INVITE_PLAYER.alreadyAnswered.eq(1))
            }.count() != 0
        }

        return answer
    }

    override fun getInvitesAccepted(idUserRole: Int): MutableList<InviteData> {
        val list = mutableListOf<InviteData>()

        transaction {
            val invites = (T_INVITE_PLAYER innerJoin T_CHAMPIONSHIP innerJoin T_GAME).select {
                T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole).and(T_INVITE_PLAYER.idChampionship_fk.eq(T_CHAMPIONSHIP.idChampionship))
                    .and(T_INVITE_PLAYER.alreadyAnswered.eq(1)).and(T_INVITE_PLAYER.accepted.eq(1))
                    .and(T_GAME.idGame.eq(T_CHAMPIONSHIP.idGame_fk))
            }

            invites.forEach {
                list.add(InviteData(it[T_INVITE_PLAYER.idPlayer_fk],
                    it[T_CHAMPIONSHIP.idChampionship],
                    it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_CHAMPIONSHIP.owner_fk],
                    it[T_GAME.nmGame],
                    it[T_INVITE_PLAYER.alreadyAnswered],
                    it[T_INVITE_PLAYER.accepted]))
            }
        }

        return list
    }

    override fun getInvitesRefused(idUserRole: Int): MutableList<InviteData> {
        val list = mutableListOf<InviteData>()

        transaction {
            val invites = (T_INVITE_PLAYER innerJoin T_CHAMPIONSHIP innerJoin T_GAME).select {
                T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole).and(T_INVITE_PLAYER.idChampionship_fk.eq(T_CHAMPIONSHIP.idChampionship))
                    .and(T_INVITE_PLAYER.alreadyAnswered.eq(1)).and(T_INVITE_PLAYER.accepted.eq(0))
                    .and(T_GAME.idGame.eq(T_CHAMPIONSHIP.idGame_fk))
            }

            invites.forEach {
                list.add(InviteData(it[T_INVITE_PLAYER.idPlayer_fk],
                    it[T_CHAMPIONSHIP.idChampionship],
                    it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_CHAMPIONSHIP.owner_fk],
                    it[T_GAME.nmGame],
                    it[T_INVITE_PLAYER.alreadyAnswered],
                    it[T_INVITE_PLAYER.accepted]))
            }
        }

        return list
    }

    override fun getInvitesNotAnswered(idUserRole: Int): MutableList<InviteData> {
        val list = mutableListOf<InviteData>()

        transaction {
            val invites = (T_INVITE_PLAYER innerJoin T_CHAMPIONSHIP innerJoin T_GAME).select {
                T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole).and(T_INVITE_PLAYER.idChampionship_fk.eq(T_CHAMPIONSHIP.idChampionship))
                    .and(T_INVITE_PLAYER.alreadyAnswered.eq(0))
                    .and(T_GAME.idGame.eq(T_CHAMPIONSHIP.idGame_fk))
            }

            invites.forEach {
                list.add(InviteData(it[T_INVITE_PLAYER.idPlayer_fk],
                    it[T_CHAMPIONSHIP.idChampionship],
                    it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_CHAMPIONSHIP.owner_fk],
                    it[T_GAME.nmGame],
                    it[T_INVITE_PLAYER.alreadyAnswered],
                    it[T_INVITE_PLAYER.accepted]))
            }
        }

        return list
    }

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
            val championships = (T_CHAMPIONSHIP innerJoin T_PLAYER_IN_CHAMPIONSHIP innerJoin T_GAME)
                .select {
                        (T_PLAYER_IN_CHAMPIONSHIP.idPlayer_fk.eq(idUserRole))
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
            println(results)
            results.forEach {
                idRole = it[T_USER_ROLE.idUserRole]
            }
        }
        println(idRole)
        return idRole
    }

    override fun getInvites(idUserRole: Int): MutableList<InviteData> {
        val list = mutableListOf<InviteData>()

        transaction {
            val invites = (T_INVITE_PLAYER innerJoin T_CHAMPIONSHIP innerJoin T_GAME).select {
                T_INVITE_PLAYER.idPlayer_fk.eq(idUserRole).and(T_INVITE_PLAYER.idChampionship_fk.eq(T_CHAMPIONSHIP.idChampionship))
                    .and(T_GAME.idGame.eq(T_CHAMPIONSHIP.idGame_fk))
            }

            invites.forEach {
                list.add(InviteData(it[T_INVITE_PLAYER.idPlayer_fk],
                    it[T_CHAMPIONSHIP.idChampionship],
                    it[T_CHAMPIONSHIP.nmChampionship],
                    it[T_CHAMPIONSHIP.owner_fk],
                    it[T_GAME.nmGame],
                    it[T_INVITE_PLAYER.alreadyAnswered],
                    it[T_INVITE_PLAYER.accepted]))
            }
        }

        return list
    }
}