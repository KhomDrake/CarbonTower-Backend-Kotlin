package com.carbontower.domain.services.player

import com.carbontower.application.web.Role
import com.carbontower.domain.entities.database.*
import com.carbontower.domain.entities.http.MatchData
import com.carbontower.domain.entities.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

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

    private fun getTime(idTime: Int) : Time{
        val players = mutableListOf<UserData>()
        var nmTime = ""

        val timesDb = (T_TEAM).select { T_TEAM.idTeam.eq(idTime) }

        timesDb.forEach {
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

        return Time(idTime, nmTime, players)
    }

    override fun timesInChampionship(idUserRole: Int, idChampionship: Int): Time {
        val players = mutableListOf<UserData>()
        var nmTime = ""
        var idTime = 0

        transaction {
            val timeDb = (T_PLAYER_IN_TEAM innerJoin T_TEAM innerJoin T_TEAM_IN_CHAMPIONSHIP).select {
                T_TEAM_IN_CHAMPIONSHIP.idChampionship_fk.eq(idChampionship)
                    .and(T_TEAM_IN_CHAMPIONSHIP.idTeam_fk.eq(T_TEAM.idTeam))
            }


            timeDb.forEach {
                idTime = it[T_TEAM.idTeam]
                nmTime = it[T_TEAM.nmTeam]
            }

            val time = getTime(idTime)

            time.players.forEach {
                players.add(it)
            }
        }

        return Time(idTime = idTime, nmTime = nmTime, players = players)
    }

    override fun getMatchsChampionship(idUserRole: Int, idChampionship: Int): List<Match> {
        val matchs = mutableListOf<Match>()
        transaction {
            val matchsDb = T_MATCH.select { T_MATCH.idChampionship_fk.eq(50) }
            matchsDb.forEach {
                val match = it
                matchs.add(
                    Match(
                        match[T_MATCH.idMatch],
                        mutableListOf(),
                        Time(0, "", listOf()),
                        match[T_MATCH.date],
                        match[T_MATCH.time]
                    )
                )
            }

            matchs.forEach {
                val match = it
                val timesDb = (T_TEAM_IN_MATCH innerJoin T_TEAM).select {
                    T_TEAM_IN_MATCH.idMatch_fk.eq(match.idMatch)
                        .and(T_TEAM_IN_MATCH.idTeam_fk.eq(T_TEAM.idTeam))
                }

                timesDb.forEach {
                    val timeDb = it
                    val time = getTime(timeDb[T_TEAM.idTeam])
                    match.times.add(time)
                    if(time.idTime == match.winner.idTime)
                        match.winner = time
                }
            }
        }
        return matchs.toList()
    }

    override fun getAllMatchsPlayer(idUserRole: Int): List<Match> {
        val matchs = mutableListOf<Match>()
        transaction {
            val teams = (T_PLAYER_IN_TEAM innerJoin T_TEAM)
                .select { T_PLAYER_IN_TEAM.idPlayer_fk.eq(idUserRole) }

            teams.forEach {
                val team = it
                val matchsDb = (T_TEAM_IN_MATCH innerJoin T_MATCH)
                    .select { T_TEAM_IN_MATCH.idTeam_fk.eq(team[T_TEAM.idTeam]) }

                val myTeam = getTime(team[T_TEAM.idTeam])

                matchsDb.forEach {
                    matchs.add(Match(it[T_MATCH.idMatch], mutableListOf(), Time(it[T_MATCH.winner], "", listOf()), it[T_MATCH.date], it[T_MATCH.time]))
                }

                val idsTimes = mutableListOf<Int>()

                matchs.forEach {
                    val match = it
                    val times = (T_TEAM_IN_MATCH innerJoin T_MATCH)
                        .select {
                            T_MATCH.idMatch.eq(it.idMatch)
                                .and(T_TEAM_IN_MATCH.idTeam_fk.neq(team[T_TEAM.idTeam]))
                        }
                    times.forEach {
                        val timeDb = it
                        if(idsTimes.contains(timeDb[T_TEAM_IN_MATCH.idTeam_fk]).not())
                            idsTimes.add(timeDb[T_TEAM_IN_MATCH.idTeam_fk])

                        val time = getTime(timeDb[T_TEAM_IN_MATCH.idTeam_fk])
                        match.times.add(time)
                        if(timeDb[T_TEAM_IN_MATCH.idTeam_fk] == match.winner.idTime)
                            match.winner = time
                        else
                            match.winner = myTeam

                        match.times.add(myTeam)
                    }
                }
            }
        }
        return matchs.toList()
    }

    override fun getAllTimesPlayers(idUserRole: Int): List<Time> {
        val times = mutableListOf<Time>()

        transaction {
            val timesDb = (T_PLAYER_IN_TEAM innerJoin T_TEAM).select {
                T_PLAYER_IN_TEAM.idPlayer_fk.eq(idUserRole).and(T_PLAYER_IN_TEAM.idTeam_fk.eq(T_TEAM.idTeam))
            }
            timesDb.forEach {
                val time = it
                println(time)
                val playersInTime = (T_USER innerJoin T_USER_ROLE innerJoin T_PLAYER_IN_TEAM).select {
                    T_PLAYER_IN_TEAM.idTeam_fk.eq(time[T_TEAM.idTeam])
                        .and(T_PLAYER_IN_TEAM.idPlayer_fk.eq(T_USER_ROLE.idUserRole))
                        .and(T_USER_ROLE.idUser_fk.eq(T_USER.idUser))
                }

                val players = mutableListOf<UserData>()
                playersInTime.forEach {
                    val userData = UserData(it[T_USER_ROLE.idUser_fk], it[T_USER.nmUser], listOf(), it[T_USER_ROLE.idUserRole])
                    players.add(userData)
                }
                times.add(Time(time[T_TEAM.idTeam], time[T_TEAM.nmTeam], players))
            }
        }

        return times
    }

    override fun getAllMatchsPlayerChampionship(idUserRole: Int, idChampionship: Int): List<Match> {
        val matchs = mutableListOf<Match>()

        transaction {
            val matchsDb = (T_PLAYER_IN_TEAM innerJoin T_TEAM innerJoin T_TEAM_IN_MATCH innerJoin T_MATCH).
                select { T_PLAYER_IN_TEAM.idPlayer_fk.eq(idUserRole).and(T_MATCH.idChampionship_fk.eq(idChampionship)) }

            matchsDb.forEach {
                val match = it
                val times = mutableListOf<Time>()
                val timesDb = (T_TEAM_IN_MATCH innerJoin T_TEAM innerJoin T_PLAYER_IN_TEAM).select { T_TEAM_IN_MATCH.idMatch_fk.eq(match[T_MATCH.idMatch])
                    .and(T_TEAM_IN_MATCH.idTeam_fk.eq(T_TEAM.idTeam))
                }

                val date = match[T_MATCH.date]
                val time = match[T_MATCH.time]
                var nmWinner = ""
                var timeWinner = mutableListOf<UserData>()
                var idWinner = 0

                timesDb.forEach {
                    val time = it
                    val playersInTime = (T_USER innerJoin T_USER_ROLE innerJoin T_PLAYER_IN_TEAM).select {
                        T_PLAYER_IN_TEAM.idTeam_fk.eq(time[T_TEAM.idTeam])
                            .and(T_PLAYER_IN_TEAM.idPlayer_fk.eq(T_USER_ROLE.idUserRole))
                            .and(T_USER_ROLE.idUser_fk.eq(T_USER.idUser))
                    }

                    val players = mutableListOf<UserData>()
                    playersInTime.forEach {
                        val userData = UserData(it[T_USER_ROLE.idUser_fk], it[T_USER.nmUser], listOf(), it[T_USER_ROLE.idUserRole])
                        players.add(userData)
                    }
                    times.add(Time(time[T_TEAM.idTeam], time[T_TEAM.nmTeam], players))
                    if(time[T_TEAM.idTeam] == match[T_MATCH.winner]) {
                        timeWinner = players
                        nmWinner = time[T_TEAM.nmTeam]
                        idWinner = time[T_TEAM.idTeam]
                    }
                }
                matchs.add(Match(match[T_MATCH.idMatch], times, Time(idWinner, nmWinner, timeWinner), date, time))
            }
        }
        return matchs.toList()
    }

    override fun getAllTimesPlayerChampionship(idUserRole: Int, idChampionship: Int): List<Time> {
        val times = mutableListOf<Time>()

        transaction {
            val timesDb = (T_TEAM_IN_CHAMPIONSHIP innerJoin T_PLAYER_IN_TEAM).select {
                T_TEAM_IN_CHAMPIONSHIP.idChampionship_fk.eq(idChampionship)
                    .and(T_PLAYER_IN_TEAM.idPlayer_fk.eq(idUserRole))
            }
            timesDb.forEach {
                val time = it
                times.add(getTime(time[T_TEAM.idTeam]))
            }
        }

        return times
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