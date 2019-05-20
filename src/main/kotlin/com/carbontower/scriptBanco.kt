package com.carbontower

/*

create table T_USER(
	idUser varchar(14) primary key,
	userPassword varchar(150),
	nmUser varchar(60)
);

create table T_ROLE(
	idRole int primary key identity(1,1),
	nmRole varchar(45)
);

create table T_USER_ROLE(
	idUserRole int primary key identity(1,1),
	idUser_fk varchar(14),
	idRole_fk int,
	foreign key(idUser_fk) references T_USER(idUser),
	foreign key(idRole_fk) references T_ROLE(idRole)
);

create table T_GAME(
	idGame int primary key identity(1,1),
	nmGame varchar(50)
);

create table T_CHAMPIONSHIP(
	idChampionship int primary key identity(1,1),
	idGame_fk int,
	owner_fk int,
	nmChampionship varchar(60),
	foreign key(idGame_fk) references T_GAME(idGame),
	foreign key(owner_fk) references T_USER_ROLE(idUserRole)
);

create table T_PLAYER_IN_CHAMPIONSHIP(
	idPlayer_fk int,
	idChampionship_fk int,
	foreign key(idPlayer_fk) references T_USER_ROLE(idUserRole),
	foreign key(idChampionship_fk) references T_CHAMPIONSHIP(idChampionship),
	primary key(idPlayer_fk,idChampionship_fk)
);

create table T_INVITE_PLAYER(
	idPlayer_fk int,
	idChampionship_fk int,
	accepted int,
	alreadyAnswered int,
	foreign key(idPlayer_fk) references T_USER_ROLE(idUserRole),
	foreign key(idChampionship_fk) references T_CHAMPIONSHIP(idChampionship),
	primary key(idPlayer_fk,idChampionship_fk)
);

create table T_ADMINISTRATOR_CHAMPIONSHIP(
	idAdministrator_fk int,
	idChampionship_fk int,
	foreign key(idAdministrator_fk) references T_USER_ROLE(idUserRole),
	foreign key(idChampionship_fk) references T_CHAMPIONSHIP(idChampionship),
	primary key(idAdministrator_fk,idChampionship_fk)
);

create table T_TEAM(
	idTeam int primary key identity(1,1)
);

create table T_PLAYER_IN_TEAM(
	idPlayer_fk int,
	idTeam_fk int,
	foreign key(idPlayer_fk) references T_USER_ROLE(idUserRole),
	foreign key(idTeam_fk) references T_TEAM(idTeam),
	primary key(idPlayer_fk,idTeam_fk)
);

create table T_MATCH(
	idMatch int primary key identity(1,1),
	idChampionship_fk int,
	foreign key(idChampionship_fk) references T_CHAMPIONSHIP(idChampionship)
);

create table T_TEAM_IN_MATCH(
	idTeam_fk int,
	idMatch_fk int,
	foreign key(idTeam_fk) references T_TEAM(idTeam),
	foreign key(idMatch_fk) references T_MATCH(idMatch),
	primary key(idTeam_fk,idMatch_fk)
);

create table T_STREAM(
	idStream int primary key identity(1,1),
	typeStream varchar(45),
	idUserRole_fk int,
	foreign key(idUserRole_fk) references T_USER_ROLE(idUserRole)
);

create table T_METRIC(
	idMetric int primary key identity(1,1),
	idStream_fk int,
	foreign key(idStream_fk) references T_STREAM(idStream)
);

create table T_MACHINE(
	idMachine int primary key identity,
	motherboard varchar(45),
	OS varchar(45),
	manufacturer varchar(45),
	model varchar(45)
);

create table T_USER_MACHINE(
	idUser_fk int,
	idMachine_fk int,
	foreign key(idUser_fk) references T_USER_ROLE(idUserRole),
	foreign key(idMachine_fk) references T_MACHINE(idMachine),
	primary key(idUser_fk,idMachine_fk)
);

create table T_MACHINE_METRIC(
	idMachineMetric int primary key identity(1,1),
	useRam decimal(6,2),
	tempGPU decimal(6,2),
	useGPU decimal(6,2),
	useDisc decimal(6,2),
	rpmCooler int,
	tempCPU decimal(6,2),
	usbDevice varchar(45),
	idMachine_fk int,
	metricDate date,
	metricTime varchar(10),
	foreign key(idMachine_fk) references T_MACHINE(idMachine)
);

/* Inserindo os roles */
insert into T_ROLE values ('Empresa');
insert into T_ROLE values ('Administrador');
insert into T_ROLE values ('Jogador');

/* Inserindo os Jogos */

insert into T_GAME values ('Dota 2');
insert into T_GAME values ('Fifa 19');
insert into T_GAME values ('Fortnite');
insert into T_GAME values ('Hearthstone');
insert into T_GAME values ('League of Legends');
insert into T_GAME values ('Overwatch');
insert into T_GAME values ('PUBG');
insert into T_GAME values ('Rainbow Six Siege');
insert into T_GAME values ('Street Fighter V');
insert into T_GAME values ('Starcraft 2');

/*
	password 123456
	BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413
*/

/* 1 */
insert into T_USER(idUser, nmUser, userPassword) values('93748506000164', 'Kholinar', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('93748506000164', 1);
/* 2 */
insert into T_USER(idUser, nmUser, userPassword) values('12166350000101', 'Jah Kaved', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('12166350000101', 1);
/* 3 */
insert into T_USER(idUser, nmUser, userPassword) values('09413516000132', 'Riot', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('09413516000132', 1);
/* 4 */
insert into T_USER(idUser, nmUser, userPassword) values('61605361000156', 'Microsoft', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('61605361000156', 1);
/* 5 */
insert into T_USER(idUser, nmUser, userPassword) values('44369680000190', 'Sony', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('44369680000190', 1);
/* 6 */
insert into T_USER(idUser, nmUser, userPassword) values('14084451000169', 'Luthadel', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('14084451000169', 1);
/* 7 */
insert into T_USER(idUser, nmUser, userPassword) values('62039797000198', 'Azir', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('62039797000198', 1);
/* 8 */
insert into T_USER(idUser, nmUser, userPassword) values('39706432000158', 'Shinovar', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('39706432000158', 1);
/* 9 */
insert into T_USER(idUser, nmUser, userPassword) values('12966665000133', 'Urithiru', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('12966665000133', 1);
/* 10 */
insert into T_USER(idUser, nmUser, userPassword) values('03930356000112', 'Narak', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('03930356000112', 1);
/* 11 */
insert into T_USER(idUser, nmUser, userPassword) values('56153132000108', 'Alethkar', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('56153132000108', 1);
/* 12 */
insert into T_USER(idUser, nmUser, userPassword) values('68167339000110', 'Marat', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('68167339000110', 1);
/* 13 */
insert into T_USER(idUser, nmUser, userPassword) values('46986097000154', 'Reshi', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('46986097000154', 1);
/* 14 */
insert into T_USER(idUser, nmUser, userPassword) values('99927886000180', 'Roshar', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('99927886000180', 1);
/* 15 */
insert into T_USER(idUser, nmUser, userPassword) values('76877844000187', 'Scadriel', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('76877844000187', 1);

/* 16 */
insert into T_USER(idUser, nmUser, userPassword) values('46099583840', 'Vinicius Lucena Viana', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('46099583840', 3);
/* 17 */
insert into T_USER(idUser, nmUser, userPassword) values('47884329867', 'Ariel', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('47884329867', 3);
/* 18 */
insert into T_USER(idUser, nmUser, userPassword) values('48257635880', 'Igor', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('48257635880', 3);
/* 19 */
insert into T_USER(idUser, nmUser, userPassword) values('77925382066', 'Kaladin', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('77925382066', 3);
/* 20 */
insert into T_USER(idUser, nmUser, userPassword) values('86762156002', 'Adolin', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('86762156002', 3);
/* 21 */
insert into T_USER(idUser, nmUser, userPassword) values('10925691038', 'Vin', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('10925691038', 3);
/* 22 */
insert into T_USER(idUser, nmUser, userPassword) values('67759159005', 'Kelsier', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('67759159005', 3);
/* 23 */
insert into T_USER(idUser, nmUser, userPassword) values('55114543032', 'Sadeas', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('55114543032', 3);
/* 24 */
insert into T_USER(idUser, nmUser, userPassword) values('46666083032', 'Odium', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('46666083032', 3);
/* 25 */
insert into T_USER(idUser, nmUser, userPassword) values('71659508002', 'Honor', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('71659508002', 3);
/* 26 */
insert into T_USER(idUser, nmUser, userPassword) values('27316610034', 'Shallan', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('27316610034', 3);
/* 27 */
insert into T_USER(idUser, nmUser, userPassword) values('19807253063', 'Rock', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('19807253063', 3);
/* 28 */
insert into T_USER(idUser, nmUser, userPassword) values('23449171011', 'Marsh', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('23449171011', 3);
/* 29 */
insert into T_USER(idUser, nmUser, userPassword) values('20958733007', 'Dalinar', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('20958733007', 3);
/* 30 */
insert into T_USER(idUser, nmUser, userPassword) values('97964675001', 'Jezrien', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('97964675001', 3);
/* 31 */
insert into T_USER(idUser, nmUser, userPassword) values('53303655030', 'Nalan', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('53303655030', 3);
/* 32 */
insert into T_USER(idUser, nmUser, userPassword) values('49721638013', 'Kalak', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('49721638013', 3);
/* 33 */
insert into T_USER(idUser, nmUser, userPassword) values('40142234001', 'Ishar', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('40142234001', 3);
/* 34 */
insert into T_USER(idUser, nmUser, userPassword) values('89881245028', 'Paliah', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('89881245028', 3);
/* 35 */
insert into T_USER(idUser, nmUser, userPassword) values('67756889037', 'Vedel', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('67756889037', 3);
/* 36 */
insert into T_USER(idUser, nmUser, userPassword) values('93399758049', 'Battar', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('93399758049', 3);
/* 37 */
insert into T_USER(idUser, nmUser, userPassword) values('83197619056', 'Yudi', 'BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413');
insert into T_USER_ROLE values('83197619056', 3);

/* Dota 2 */
insert into T_CHAMPIONSHIP values (1, 10, 'Minor de Bucareste')
insert into T_CHAMPIONSHIP values (1, 11, 'DreamLeague')
insert into T_CHAMPIONSHIP values (1, 13, 'StarLadder Invitational')
insert into T_CHAMPIONSHIP values (1, 5, 'ESL One Katowice')
insert into T_CHAMPIONSHIP values (1, 2, 'StarLadder Invitational')

/* Fifa 19 */
insert into T_CHAMPIONSHIP values (2, 1, 'FUT Champions Cup')
insert into T_CHAMPIONSHIP values (2, 7, 'FIFA eClub World Cup')
insert into T_CHAMPIONSHIP values (2, 8, 'Gfinity FIFA Series')
insert into T_CHAMPIONSHIP values (2, 10, 'Gfinity FUT Champions')
insert into T_CHAMPIONSHIP values (2, 3, 'eChampions League')

/* Fortnite */
insert into T_CHAMPIONSHIP values (3, 2, 'Fortnite Summer Smash')
insert into T_CHAMPIONSHIP values (3, 15, 'Secret Skirmish')
insert into T_CHAMPIONSHIP values (3, 2, 'ESL Katowice Royale')
insert into T_CHAMPIONSHIP values (3, 4, 'Fortnite World Cup - Qualificatórias')
insert into T_CHAMPIONSHIP values (3, 8, 'Fortnite World Cup (Copa do Mundo)')

/* Hearthstone */
insert into T_CHAMPIONSHIP values (4, 9, 'HCT Winter - Qualificatória das Américas')
insert into T_CHAMPIONSHIP values (4, 4, 'HCT Winter')
insert into T_CHAMPIONSHIP values (4, 3, 'WCG - Qualificatória das Américas')
insert into T_CHAMPIONSHIP values (4, 2, 'Hearthstone Masters Tour Seul')
insert into T_CHAMPIONSHIP values (4, 1, 'Hearthstone Grandmasters')

/* League of Legends */
insert into T_CHAMPIONSHIP values (5, 11, 'CBLOL 1° Etapa')
insert into T_CHAMPIONSHIP values (5, 10, 'Circuito Desafiante')
insert into T_CHAMPIONSHIP values (5, 14, 'Mid-Season Invitational')
insert into T_CHAMPIONSHIP values (5, 12, 'CBLoL 2ª Etapa: 1º ')
insert into T_CHAMPIONSHIP values (5, 13, 'Circuito Desafiante')

/* Overwatch */
insert into T_CHAMPIONSHIP values (6, 7, 'Overwatch Contenders')
insert into T_CHAMPIONSHIP values (6, 6, 'Duelo do Atlântico')
insert into T_CHAMPIONSHIP values (6, 4, 'Overwatch League - All-Star Game')
insert into T_CHAMPIONSHIP values (6, 3, 'Overwatch League - Playoffs')
insert into T_CHAMPIONSHIP values (6, 5, 'Overwatch World Cup')

/* PUBG */
insert into T_CHAMPIONSHIP values (7, 10, 'ESL LA League')
insert into T_CHAMPIONSHIP values (7, 15, 'FACEIT Global Summit')
insert into T_CHAMPIONSHIP values (7, 12, 'ESL LA League')
insert into T_CHAMPIONSHIP values (7, 11, 'All-Star Games')
insert into T_CHAMPIONSHIP values (7, 10, 'PUBG Global Championship')

/* Rainbow Six Siege */
insert into T_CHAMPIONSHIP values (8, 2, 'Brasileirão')
insert into T_CHAMPIONSHIP values (8, 4, 'Six Invitational')
insert into T_CHAMPIONSHIP values (8, 6, 'Minor de Las Vegas')
insert into T_CHAMPIONSHIP values (8, 8, 'DreamHack Valência')
insert into T_CHAMPIONSHIP values (8, 10, 'Six Major')

/* Street Fighter V */
insert into T_CHAMPIONSHIP values (9, 1, 'Final Round')
insert into T_CHAMPIONSHIP values (9, 3, 'NorCal Regionals')
insert into T_CHAMPIONSHIP values (9, 5, 'Combo Breaker')
insert into T_CHAMPIONSHIP values (9, 7, 'Game Over')
insert into T_CHAMPIONSHIP values (9, 9, 'Thunderstruck')

/* Starcraft 2 */
insert into T_CHAMPIONSHIP values (10, 1, 'WCS Winter')
insert into T_CHAMPIONSHIP values (10, 4, 'WCS Summer')
insert into T_CHAMPIONSHIP values (10, 8, 'IEM Katowice')
insert into T_CHAMPIONSHIP values (10, 12, 'GSL vs the World')
insert into T_CHAMPIONSHIP values (10, 15, 'WCS Spring')


/* convites */
insert into T_INVITE_PLAYER values (16, 1, 0, 0);
insert into T_INVITE_PLAYER values (17, 2, 0, 0);
insert into T_INVITE_PLAYER values (18, 3, 0, 0);
insert into T_INVITE_PLAYER values (19, 4, 0, 0);
insert into T_INVITE_PLAYER values (20, 5, 0, 0);
insert into T_INVITE_PLAYER values (21, 6, 0, 0);
insert into T_INVITE_PLAYER values (22, 7, 0, 0);
insert into T_INVITE_PLAYER values (23, 8, 0, 0);
insert into T_INVITE_PLAYER values (24, 9, 0, 0);
insert into T_INVITE_PLAYER values (25, 10, 0, 0);
insert into T_INVITE_PLAYER values (26, 11, 0, 0);
insert into T_INVITE_PLAYER values (27, 12, 0, 0);
insert into T_INVITE_PLAYER values (28, 13, 0, 0);
insert into T_INVITE_PLAYER values (29, 14, 0, 0);
insert into T_INVITE_PLAYER values (30, 15, 0, 0);
insert into T_INVITE_PLAYER values (31, 1, 0, 0);
insert into T_INVITE_PLAYER values (32, 2, 0, 0);
insert into T_INVITE_PLAYER values (33, 3, 0, 0);
insert into T_INVITE_PLAYER values (34, 4, 0, 0);
insert into T_INVITE_PLAYER values (35, 5, 0, 0);
insert into T_INVITE_PLAYER values (36, 6, 0, 0);
insert into T_INVITE_PLAYER values (37, 7, 0, 0);

select * from T_USER_ROLE;

/* Máquinas */
insert into T_MACHINE values ('LENOVO 1 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 2 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 3 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 4 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 5 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 6 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 7 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 8 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 9 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 10 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 11 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 12 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 13 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 14 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');
insert into T_MACHINE values ('LENOVO 15 SDK0J40688 WIN', 'Microsoft Windows 10(home) build 17763', 'LENOVO', '81FE');


/* Máquinas Player */
insert into T_USER_MACHINE values (16, 1)
insert into T_USER_MACHINE values (17, 2)
insert into T_USER_MACHINE values (19, 3)
insert into T_USER_MACHINE values (20, 4)
insert into T_USER_MACHINE values (22, 5)
insert into T_USER_MACHINE values (23, 6)
insert into T_USER_MACHINE values (25, 7)
insert into T_USER_MACHINE values (26, 8)
insert into T_USER_MACHINE values (27, 9)
insert into T_USER_MACHINE values (28, 10)
insert into T_USER_MACHINE values (29, 11)
insert into T_USER_MACHINE values (30, 12)
insert into T_USER_MACHINE values (31, 13)
insert into T_USER_MACHINE values (32, 14)
insert into T_USER_MACHINE values (33, 15)

select * from T_MACHINE;

select count(*) from T_MACHINE_METRIC;



/*
	pegar jogos
	insertMaquina
	getMaquina
	insertMedicaoMaquina
	getMaquinas
	getMedicoes
	getTime
	getPartida
	isAdministrador
	getAdministradores
*/

/* Comandos para dropar as tabelas
drop table T_MACHINE_METRIC;
drop table T_USER_MACHINE;
drop table T_MACHINE;
drop table T_METRIC;
drop table T_STREAM;
drop table T_TEAM_IN_MATCH;
drop table T_MATCH;
drop table T_PLAYER_IN_TEAM;
drop table T_TEAM;
drop table T_ADMINISTRATOR_CHAMPIONSHIP;
drop table T_INVITE_PLAYER;
drop table T_PLAYER_IN_CHAMPIONSHIP;
drop table T_CHAMPIONSHIP;
drop table T_GAME;
drop table T_USER_ROLE;
drop table T_ROLE;
drop table T_USER;
*/
 */