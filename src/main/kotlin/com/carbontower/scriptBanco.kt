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
	idTeam int primary key identity(1,1),
	nmTeam varchar(50)
);

create table T_PLAYER_IN_TEAM(
	idPlayer_fk int,
	idTeam_fk int,
	foreign key(idPlayer_fk) references T_USER_ROLE(idUserRole),
	foreign key(idTeam_fk) references T_TEAM(idTeam),
	primary key(idPlayer_fk,idTeam_fk)
);

create table T_TEAM_IN_CHAMPIONSHIP(
	idChampionship_fk int,
	idTeam_fk int,
	foreign key(idChampionship_fk) references T_CHAMPIONSHIP(idChampionship),
	foreign key(idTeam_fk) references T_TEAM(idTeam),
	primary key(idChampionship_fk,idTeam_fk)
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
	useCPU decimal(6,2),
	rpmCooler int,
	tempCPU decimal(6,2),
	usbDevice varchar(45),
	idMachine_fk int,
	metricDate varchar(12),
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

/*

95205316020
89340690079
61720259020
56544241067
80497240009
28900538063
56877016050
62699451002
32701435080
02295176093
41139537083
43118661003
03074124075
70351432035
81397436077
93119210099
34119781046
28688544000
80072634030
36274562036
28483462052
42257562038
29437263035
00361586078
69541472060
93520221063
17179110051
96844051034
46138128010
49094850079
08991049087
04434724088
60058171088
02535254034
30920373089
14316994034
02534269011
62640042009
06160316028
06702720009
58625972047
26180113092
44476102050
99212869009
21023987066
66243561038
84721118066
13959948034
24541336014
07461117033
92018673017
35663248099
25599749088
56107196072
82021776000
72204753068
96224651027
94912690031
73198650097
88990640083
73437070002
10352445009
52128331080
25554285007
67544625079
92357946091
76413700041
85826131055
23084635030
43543039067
29261527019
36895258027
05262348068
56283984037
64679646080
83786943052
37380339044
64189729027
23646376007
14873072093
69062437052
89505491077
37113920080
68428423016
37381406039
26305758026
72557073008
42976516022
56593370070
01411419057
85560863068
26488545094
91044422025
84036822047
14380661040
26313059069
42766325018
31082949035
92663568082
67686737004
12747031071
76456708001
65038342094
75855154050
59083912043
75749235076
09422583047
47762064044
87043499038
71056039094
07787015063
89851813001
07035195068
18504177092
40025780034
59207851016
26764041059
20698814070
55080828080
43353877096
98501390054
52592666095
80323693091
05657567021
55665722006
28453067090
85754008007
88414248004
11647234050
08482224077
78144495020
78739005097
48958410060
59171612084
10708070019
43967005046
59418451034
10944459080
93189291020
92990778004
69633148022
31097284000
52731931094
84407350067
09821332005
11604219009
34500380019
94378106051
01874473064
43258551065
75575217060
41733237003
68928950007
14982396094
94621370057
95356454040
62610443099
30430222033
01667955004
30699941059
47928315041
39840963058
86545208098
38261982041
45101311006
53113778018
10946893004
66808382077
50189299010
59322699019
48547625003
59084002032
57939319027
95073538013
30073604046
34611553060
32412989060
63802724020
13293460097
81053644094
73219211070
23022484062
96497694048
54451809053
20598109064
27330651060
87500520042
52285604084
49442808079
43712061072
62683331010
66399184045
76219203003
60584789017
77147898087
81526713047
08226409020
03566474045
80332175022
84285793008
32882400098


 */



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

/* Times */
/* 1 */
insert into T_TEAM values('Febi');
/* 2 */
insert into T_TEAM values('Erwoe');
/* 3 */
insert into T_TEAM values('Powoebua');
/* 4 */
insert into T_TEAM values('Baholar');
/* 5 */
insert into T_TEAM values('Nuyviu');
/* 6 */
insert into T_TEAM values('Zuimo');
/* 7 */
insert into T_TEAM values('Woneki');
/* 8 */
insert into T_TEAM values('Betupo');
/* 9 */
insert into T_TEAM values('Sixas');
/* 10 */
insert into T_TEAM values('Brixu');
/* 11 */
insert into T_TEAM values('Rurao');
/* 12 */
insert into T_TEAM values('Meope');
/* 13 */
insert into T_TEAM values('Tuzya');
/* 14 */
insert into T_TEAM values('Roirn');
/* 15 */
insert into T_TEAM values('Wewae');
/* 16 */
insert into T_TEAM values('Buesua');
/* 17 */
insert into T_TEAM values('Kidau');
/* 18 */
insert into T_TEAM values('Xyufa');
/* 19 */
insert into T_TEAM values('Rofeu');
/* 20 */
insert into T_TEAM values('Roas');
/* 21 */
insert into T_TEAM values('Cofal');
/* 22 */
insert into T_TEAM values('Haefa');
/* 23 */
insert into T_TEAM values('Simas');
/* 24 */
insert into T_TEAM values('Haykoil');
/* 25 */
insert into T_TEAM values('Fuzon');
/* 26 */
insert into T_TEAM values('Irtir');
/* 27 */
insert into T_TEAM values('Xeotuman');
/* 28 */
insert into T_TEAM values('Xexera');
/* 29 */
insert into T_TEAM values('Asnoabu');
/* 30 */
insert into T_TEAM values('Geimo');
/* 31 */
insert into T_TEAM values('Issir');
/* 32 */
insert into T_TEAM values('Wuhua');

/* Time In Championship */
insert into T_TEAM_IN_CHAMPIONSHIP values (1,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (1,2);
insert into T_TEAM_IN_CHAMPIONSHIP values (1,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (1,7);
insert into T_TEAM_IN_CHAMPIONSHIP values (1,6);

insert into T_TEAM_IN_CHAMPIONSHIP values (2,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (2,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (2,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (2,17);
insert into T_TEAM_IN_CHAMPIONSHIP values (2,23);

insert into T_TEAM_IN_CHAMPIONSHIP values (3,2);
insert into T_TEAM_IN_CHAMPIONSHIP values (3,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (3,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (3,28);
insert into T_TEAM_IN_CHAMPIONSHIP values (3,30);

insert into T_TEAM_IN_CHAMPIONSHIP values (4,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (4,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (4,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (4,28);
insert into T_TEAM_IN_CHAMPIONSHIP values (4,4);

insert into T_TEAM_IN_CHAMPIONSHIP values (5,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (5,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (5,24);
insert into T_TEAM_IN_CHAMPIONSHIP values (5,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (5,1);

insert into T_TEAM_IN_CHAMPIONSHIP values (6,10);
insert into T_TEAM_IN_CHAMPIONSHIP values (6,8);
insert into T_TEAM_IN_CHAMPIONSHIP values (6,18);
insert into T_TEAM_IN_CHAMPIONSHIP values (6,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (6,29);

insert into T_TEAM_IN_CHAMPIONSHIP values (7,7);
insert into T_TEAM_IN_CHAMPIONSHIP values (7,25);
insert into T_TEAM_IN_CHAMPIONSHIP values (7,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (7,14);
insert into T_TEAM_IN_CHAMPIONSHIP values (7,13);

insert into T_TEAM_IN_CHAMPIONSHIP values (8,18);
insert into T_TEAM_IN_CHAMPIONSHIP values (8,9);
insert into T_TEAM_IN_CHAMPIONSHIP values (8,16);
insert into T_TEAM_IN_CHAMPIONSHIP values (8,4);
insert into T_TEAM_IN_CHAMPIONSHIP values (8,23);

insert into T_TEAM_IN_CHAMPIONSHIP values (9,17);
insert into T_TEAM_IN_CHAMPIONSHIP values (9,6);
insert into T_TEAM_IN_CHAMPIONSHIP values (9,29);
insert into T_TEAM_IN_CHAMPIONSHIP values (9,27);
insert into T_TEAM_IN_CHAMPIONSHIP values (9,20);

insert into T_TEAM_IN_CHAMPIONSHIP values (10,29);
insert into T_TEAM_IN_CHAMPIONSHIP values (10,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (10,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (10,6);
insert into T_TEAM_IN_CHAMPIONSHIP values (10,14);

insert into T_TEAM_IN_CHAMPIONSHIP values (11,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (11,4);
insert into T_TEAM_IN_CHAMPIONSHIP values (11,24);
insert into T_TEAM_IN_CHAMPIONSHIP values (11,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (11,15);

insert into T_TEAM_IN_CHAMPIONSHIP values (12,24);
insert into T_TEAM_IN_CHAMPIONSHIP values (12,6);
insert into T_TEAM_IN_CHAMPIONSHIP values (12,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (12,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (12,28);

insert into T_TEAM_IN_CHAMPIONSHIP values (13,19);
insert into T_TEAM_IN_CHAMPIONSHIP values (13,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (13,8);
insert into T_TEAM_IN_CHAMPIONSHIP values (13,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (13,2);

insert into T_TEAM_IN_CHAMPIONSHIP values (14,6);
insert into T_TEAM_IN_CHAMPIONSHIP values (14,22);
insert into T_TEAM_IN_CHAMPIONSHIP values (14,24);
insert into T_TEAM_IN_CHAMPIONSHIP values (14,13);
insert into T_TEAM_IN_CHAMPIONSHIP values (14,24);

insert into T_TEAM_IN_CHAMPIONSHIP values (15,25);
insert into T_TEAM_IN_CHAMPIONSHIP values (15,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (15,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (15,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (15,16);

insert into T_TEAM_IN_CHAMPIONSHIP values (16,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (16,23);
insert into T_TEAM_IN_CHAMPIONSHIP values (16,24);
insert into T_TEAM_IN_CHAMPIONSHIP values (16,4);
insert into T_TEAM_IN_CHAMPIONSHIP values (16,20);

insert into T_TEAM_IN_CHAMPIONSHIP values (17,25);
insert into T_TEAM_IN_CHAMPIONSHIP values (17,8);
insert into T_TEAM_IN_CHAMPIONSHIP values (17,14);
insert into T_TEAM_IN_CHAMPIONSHIP values (17,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (17,12);

insert into T_TEAM_IN_CHAMPIONSHIP values (18,29);
insert into T_TEAM_IN_CHAMPIONSHIP values (18,22);
insert into T_TEAM_IN_CHAMPIONSHIP values (18,27);
insert into T_TEAM_IN_CHAMPIONSHIP values (18,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (18,5);

insert into T_TEAM_IN_CHAMPIONSHIP values (19,16);
insert into T_TEAM_IN_CHAMPIONSHIP values (19,28);
insert into T_TEAM_IN_CHAMPIONSHIP values (19,9);
insert into T_TEAM_IN_CHAMPIONSHIP values (19,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (19,10);

insert into T_TEAM_IN_CHAMPIONSHIP values (20,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (20,23);
insert into T_TEAM_IN_CHAMPIONSHIP values (20,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (20,20);
insert into T_TEAM_IN_CHAMPIONSHIP values (20,12);

insert into T_TEAM_IN_CHAMPIONSHIP values (21,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (21,18);
insert into T_TEAM_IN_CHAMPIONSHIP values (21,14);
insert into T_TEAM_IN_CHAMPIONSHIP values (21,8);
insert into T_TEAM_IN_CHAMPIONSHIP values (21,11);

insert into T_TEAM_IN_CHAMPIONSHIP values (22,23);
insert into T_TEAM_IN_CHAMPIONSHIP values (22,14);
insert into T_TEAM_IN_CHAMPIONSHIP values (22,6);
insert into T_TEAM_IN_CHAMPIONSHIP values (22,26);
insert into T_TEAM_IN_CHAMPIONSHIP values (22,23);

insert into T_TEAM_IN_CHAMPIONSHIP values (23,14);
insert into T_TEAM_IN_CHAMPIONSHIP values (23,25);
insert into T_TEAM_IN_CHAMPIONSHIP values (23,13);
insert into T_TEAM_IN_CHAMPIONSHIP values (23,2);
insert into T_TEAM_IN_CHAMPIONSHIP values (23,12);

insert into T_TEAM_IN_CHAMPIONSHIP values (24,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (24,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (24,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (24,29);
insert into T_TEAM_IN_CHAMPIONSHIP values (24,23);

insert into T_TEAM_IN_CHAMPIONSHIP values (25,17);
insert into T_TEAM_IN_CHAMPIONSHIP values (25,20);
insert into T_TEAM_IN_CHAMPIONSHIP values (25,24);
insert into T_TEAM_IN_CHAMPIONSHIP values (25,27);
insert into T_TEAM_IN_CHAMPIONSHIP values (25,5);

insert into T_TEAM_IN_CHAMPIONSHIP values (26,28);
insert into T_TEAM_IN_CHAMPIONSHIP values (26,20);
insert into T_TEAM_IN_CHAMPIONSHIP values (26,20);
insert into T_TEAM_IN_CHAMPIONSHIP values (26,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (26,3);

insert into T_TEAM_IN_CHAMPIONSHIP values (27,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (27,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (27,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (27,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (27,3);

insert into T_TEAM_IN_CHAMPIONSHIP values (28,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (28,27);
insert into T_TEAM_IN_CHAMPIONSHIP values (28,22);
insert into T_TEAM_IN_CHAMPIONSHIP values (28,19);
insert into T_TEAM_IN_CHAMPIONSHIP values (28,2);

insert into T_TEAM_IN_CHAMPIONSHIP values (29,18);
insert into T_TEAM_IN_CHAMPIONSHIP values (29,9);
insert into T_TEAM_IN_CHAMPIONSHIP values (29,23);
insert into T_TEAM_IN_CHAMPIONSHIP values (29,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (29,28);

insert into T_TEAM_IN_CHAMPIONSHIP values (30,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (30,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (30,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (30,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (30,16);

insert into T_TEAM_IN_CHAMPIONSHIP values (31,9);
insert into T_TEAM_IN_CHAMPIONSHIP values (31,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (31,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (31,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (31,12);

insert into T_TEAM_IN_CHAMPIONSHIP values (32,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (32,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (32,28);
insert into T_TEAM_IN_CHAMPIONSHIP values (32,24);
insert into T_TEAM_IN_CHAMPIONSHIP values (32,23);

insert into T_TEAM_IN_CHAMPIONSHIP values (33,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (33,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (33,9);
insert into T_TEAM_IN_CHAMPIONSHIP values (33,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (33,22);

insert into T_TEAM_IN_CHAMPIONSHIP values (34,25);
insert into T_TEAM_IN_CHAMPIONSHIP values (34,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (34,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (34,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (34,31);

insert into T_TEAM_IN_CHAMPIONSHIP values (35,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (35,20);
insert into T_TEAM_IN_CHAMPIONSHIP values (35,22);
insert into T_TEAM_IN_CHAMPIONSHIP values (35,23);
insert into T_TEAM_IN_CHAMPIONSHIP values (35,13);

insert into T_TEAM_IN_CHAMPIONSHIP values (36,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (36,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (36,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (36,2);
insert into T_TEAM_IN_CHAMPIONSHIP values (36,9);

insert into T_TEAM_IN_CHAMPIONSHIP values (37,14);
insert into T_TEAM_IN_CHAMPIONSHIP values (37,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (37,10);
insert into T_TEAM_IN_CHAMPIONSHIP values (37,35);
insert into T_TEAM_IN_CHAMPIONSHIP values (37,19);

insert into T_TEAM_IN_CHAMPIONSHIP values (38,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (38,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (38,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (38,17);
insert into T_TEAM_IN_CHAMPIONSHIP values (38,14);

insert into T_TEAM_IN_CHAMPIONSHIP values (39,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (39,29);
insert into T_TEAM_IN_CHAMPIONSHIP values (39,4);
insert into T_TEAM_IN_CHAMPIONSHIP values (39,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (39,21);

insert into T_TEAM_IN_CHAMPIONSHIP values (40,4);
insert into T_TEAM_IN_CHAMPIONSHIP values (40,7);
insert into T_TEAM_IN_CHAMPIONSHIP values (40,18);
insert into T_TEAM_IN_CHAMPIONSHIP values (40,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (40,22);

insert into T_TEAM_IN_CHAMPIONSHIP values (41,30);
insert into T_TEAM_IN_CHAMPIONSHIP values (41,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (41,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (41,7);
insert into T_TEAM_IN_CHAMPIONSHIP values (41,4);

insert into T_TEAM_IN_CHAMPIONSHIP values (42,25);
insert into T_TEAM_IN_CHAMPIONSHIP values (42,12);
insert into T_TEAM_IN_CHAMPIONSHIP values (42,6);
insert into T_TEAM_IN_CHAMPIONSHIP values (42,25);
insert into T_TEAM_IN_CHAMPIONSHIP values (42,7);

insert into T_TEAM_IN_CHAMPIONSHIP values (43,22);
insert into T_TEAM_IN_CHAMPIONSHIP values (43,5);
insert into T_TEAM_IN_CHAMPIONSHIP values (43,26);
insert into T_TEAM_IN_CHAMPIONSHIP values (43,4);
insert into T_TEAM_IN_CHAMPIONSHIP values (43,10);

insert into T_TEAM_IN_CHAMPIONSHIP values (44,17);
insert into T_TEAM_IN_CHAMPIONSHIP values (44,31);
insert into T_TEAM_IN_CHAMPIONSHIP values (44,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (44,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (44,28);

insert into T_TEAM_IN_CHAMPIONSHIP values (45,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (45,17);
insert into T_TEAM_IN_CHAMPIONSHIP values (45,4);
insert into T_TEAM_IN_CHAMPIONSHIP values (45,10);
insert into T_TEAM_IN_CHAMPIONSHIP values (45,11);

insert into T_TEAM_IN_CHAMPIONSHIP values (46,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (46,23);
insert into T_TEAM_IN_CHAMPIONSHIP values (46,2);
insert into T_TEAM_IN_CHAMPIONSHIP values (46,18);
insert into T_TEAM_IN_CHAMPIONSHIP values (46,25);

insert into T_TEAM_IN_CHAMPIONSHIP values (47,10);
insert into T_TEAM_IN_CHAMPIONSHIP values (47,28);
insert into T_TEAM_IN_CHAMPIONSHIP values (47,23);
insert into T_TEAM_IN_CHAMPIONSHIP values (47,17);
insert into T_TEAM_IN_CHAMPIONSHIP values (47,14);

insert into T_TEAM_IN_CHAMPIONSHIP values (48,16);
insert into T_TEAM_IN_CHAMPIONSHIP values (48,9);
insert into T_TEAM_IN_CHAMPIONSHIP values (48,21);
insert into T_TEAM_IN_CHAMPIONSHIP values (48,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (48,10);

insert into T_TEAM_IN_CHAMPIONSHIP values (49,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (49,14);
insert into T_TEAM_IN_CHAMPIONSHIP values (49,3);
insert into T_TEAM_IN_CHAMPIONSHIP values (49,26);
insert into T_TEAM_IN_CHAMPIONSHIP values (49,9);

insert into T_TEAM_IN_CHAMPIONSHIP values (50,1);
insert into T_TEAM_IN_CHAMPIONSHIP values (50,28);
insert into T_TEAM_IN_CHAMPIONSHIP values (50,11);
insert into T_TEAM_IN_CHAMPIONSHIP values (50,15);
insert into T_TEAM_IN_CHAMPIONSHIP values (50,21);


/* Player In Team */


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

/* Comandos para dropar as tabelas */
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
package com.carbontower

