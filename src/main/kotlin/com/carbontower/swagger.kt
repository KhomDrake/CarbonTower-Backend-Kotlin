package com.carbontower

/*

swagger: "2.0"
info:
  description: "Essa é a documentação da API da Carbon Tower para uso do grupo da faculdade"
  version: "1.0.0"
  title: "Carbon Tower Backend"
host: "localhost:8000"
tags:
- name: "login"
- name: "logout"
- name: "championship"
- name: "data"
- name: "machine"
- name: "player"
- name: "signup"
schemes:
- "http"

paths:
    /login:
      post:
        tags:
        - "login"
        summary: "Loga o usuário"
        description: "Loga o usuário"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para Logar"
          required: true
          schema:
            $ref: "#/definitions/LoginData"
        responses:
          200:
            description: "Login Feito com Sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Login Feito com Sucesso"
    /login/java:
      post:
        tags:
        - "login"
        summary: "Loga o usuário"
        description: "Loga o usuário mas não gera o cookie"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para Logar"
          required: true
          schema:
            $ref: "#/definitions/LoginData"
        responses:
          200:
            description: "Login Feito com Sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Login Feito com Sucesso"
    /login/cookie:
      post:
        tags:
        - "login"
        summary: "Loga o usuário"
        description: "Loga o usuário, gera o cookie e retorna o valor dele"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para Logar"
          required: true
          schema:
            $ref: "#/definitions/LoginData"
        responses:
          200:
            description: "Login Feito com Sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Login Feito com Sucesso"
    /login/logout:
      get:
        tags:
        - "logout"
        summary: "Logout do Usuário | Necessário Cookie"
        description: "Destroi o cookie"
        responses:
          200:
            description: OK
    /campeonato/signup:
      post:
        tags:
        - "championship"
        summary: "Cadastro de Campeonato | Necessário Cookie"
        description: "Cadastro de Campeonato"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para cadastro de Campeonato"
          required: true
          schema:
            $ref: "#/definitions/SingupChampionshipData"
        responses:
          200:
            description: "Cadastro feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Cadastro feito com sucesso"
    /campeonato/get:
      get:
        tags:
        - "championship"
        summary: "Retorna os campeonatos de uma empresa ! Necessário Cookie"
        description: "Retorna os campeonatos de uma empresa"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/ChampionshipDataList"
    /campeonato/invites/:idchampionship:
      get:
        tags:
        - "championship"
        summary: "Retorna todos os convites de um campeonato | Necessário Cookie"
        description: "Retorna os campeonatos de uma empresa"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/InviteTotalDataList"
    /campeonato/detail/:idchampionship:
      get:
        tags:
        - "championship"
        summary: "Retorna os detalhes de um campeonato | Necessário Cookie"
        description: "Retorna os detalhes de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/ChampionshipData"
    /campeonato/:idchampionship/players:
      get:
        tags:
        - "championship"
        summary: "Retorna os jogadores de um campeonato | Necessário Cookie"
        description: "Retorna os jogadores de um campeonato"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/PlayerChampionshipDataList"
    /campeonato/invite/:idchampionship/create:
      post:
        tags:
        - "championship"
        summary: "Cria Convite para um campeonato | Necessário Cookie"
        description: "Cria Convite para um campeonato"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para cadastro de Convite"
          required: true
          schema:
            $ref: "#/definitions/InviteCreateData"
        responses:
          200:
            description: "Cadastro de convite feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Cadastro de convite feito com sucesso"
    /campeonato/games:
      post:
        tags:
        - "championship"
        summary: "Retorna todos os games cadastrado no banco"
        description: "Retorna todos os games cadastrado no banco"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/GameDataList"
    /data/validate:
      get:
        tags:
        - "data"
        summary: "Retorna um boolean se alguém está logado no navegador | Necessário Cookie"
        description: "Retorna um boolean se alguém está logado no navegador"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: boolean
                  description: "Existe alguém logado"
    /data/role:
      get:
        tags:
        - "data"
        summary: "Retorna o papel principal do usuário Logado | Necessário Cookie"
        description: "Retorna o papel principal do usuário Logado"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: string
                  description: "Papel principal do usuário logado"
    /data/idrole-empresa:
      get:
        tags:
        - "data"
        summary: "Retorna o id onde o papel do usuário é Empresa | Necessário Cookie"
        description: "Retorna o id onde o papel do usuário é Empresa"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: integer
                  description: "Id Papel Usuário"
    /data/idrole-jogador:
      get:
        tags:
        - "data"
        summary: "Retorna o id onde o papel do usuário é jogador | Necessário Cookie"
        description: "Retorna o id onde o papel do usuário é jogador"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: integer
                  description: "Id Papel Usuário"

    /data/idrole-administrador:
      get:
        tags:
        - "data"
        summary: "Retorna o id onde o papel do usuário é administrador | Necessário Cookie"
        description: "Retorna o id onde o papel do usuário é administrador"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: integer
                  description: "Id Papel Usuário"
    /data/user:
      get:
        tags:
        - "data"
        summary: "Retorna o dado do usuário Logado | Necessário Cookie"
        description: "Retorna o dado do usuário Logado"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/UserData"
    /machine:
      post:
        tags:
        - "machine"
        summary: "Cadastra Máquina"
        description: "Cadastra Máquina"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para cadastro de Máquina"
          required: true
          schema:
            $ref: "#/definitions/InsertMachineData"
        responses:
          200:
            description: "Cadastro feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Cadastro feito com sucesso"
      get:
        tags:
        - "machine"
        summary: "Retorna máquinas do jogador logado | Necessário Cookie"
        description: "Retorna máquinas do jogador logado"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/MachineDataList"
    /machine/:idMachine:
      get:
        tags:
        - "machine"
        description: "Retorna dados da máquina | Necessário Cookie"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/MachineData"
    /machine/metric/:idMachine:
      post:
        tags:
        - "machine"
        summary: "Cadastro de métrica em uma máquina | Necessário Cookie"
        description: "Cadastro de métrica em uma máquina"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para cadastro de Métrica"
          required: true
          schema:
            $ref: "#/definitions/InsertMetricMachineData"
        responses:
          200:
            description: "Cadastro de métrica feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Cadastro de métrica feito com sucesso"
    /machine/metric/by-date/:idMachine:
      post:
        tags:
        - "machine"
        summary: "Retorna métricas de uma máquina de uma certa data | Necessário Cookie"
        description: "Retorna métricas de uma máquina de uma certa data"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Data que quer as métricas"
          required: true
          schema:
            $ref: "#/definitions/DateMetricMachineData"
        responses:
          200:
            description: "Métricas retornadas com sucesso"
            schema:
              $ref: "#/definitions/MachineMetricDataList"
    /player/invites/:idchampionship/accept:
      get:
        tags:
        - "player"
        summary: "Aceita convite de um cameponato | Necessário Cookie"
        description: "Aceita convite de um cameponato"
        responses:
          200:
            description: "Aceite feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Aceite feito com sucesso"
    /player/invites/:idchampionship/refuse:
      get:
        tags:
        - "player"
        summary: "Recusa convite de um cameponato | Necessário Cookie"
        description: "Recusa convite de um cameponato"
        responses:
          200:
            description: "Recusa feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Recusa feito com sucesso"
    /player/all-invites:
      get:
        tags:
        - "player"
        summary: "Retorna todos os convites de um jogador | Necessário Cookie"
        description: "Retorna todos os convites de um jogador"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/InviteDataList"
    /player/championship:
      get:
        tags:
        - "player"
        summary: "Retorna todos os campeonatos que um jogador participa | Necessário Cookie"
        description: "Retorna todos os campeonatos que um jogador participa"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/ChampionshipDataList"
    /player/administrator:
      get:
        tags:
        - "player"
        summary: "Retorna todos os campeonatos que o jogador administra | Necessário Cookie"
        description: "Retorna todos os campeonatos que o jogador administra"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/ChampionshipDataList"
    /player/administrator/:idchampionship:
      get:
        tags:
        - "player"
        summary: "Retorna um boolean informando se o jogador administra o campeonato | Necessário Cookie"
        description: "Retorna um boolean informando se o jogador administra o campeonato"
        responses:
          200:
            description: "Jogador administra campeonato"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Jogador administra campeonato"
    /signup:
      post:
        tags:
        - "signup"
        summary: "Cadastro de PF ou PJ"
        description: "Cadastro de PF ou PJ"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação para cadastro"
          required: true
          schema:
            $ref: "#/definitions/DateMetricMachineData"
        responses:
          200:
            description: "Cadastro feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Cadastro feito com sucesso"

definitions:
  DateMetricMachineData:
    type: object
    properties:
      date:
        description: "Data da Medição"
        type: string
  InsertMachineData:
    type: object
    properties:
      motherBoard:
        description: "Placa Mãe"
        type: string
      os:
        description: "Sistema Operacional"
        type: string
      manufacturer:
        description: "Fabricante"
        type: string
      model:
        description: "Modelo"
        type: string
  InsertMetricMachineData:
    type: object
    properties:
      useRam:
        description: "Razão Social do cliente"
        type: number
      tempGPU:
        description: "Razão Social do cliente"
        type: number
      useGPU:
        description: "Razão Social do cliente"
        type: number
      useCPU:
        description: "Razão Social do cliente"
        type: number
      useDisc:
        description: "Razão Social do cliente"
        type: number
      rpmCooler:
        description: "Razão Social do cliente"
        type: integer
      tempCPU:
        description: "Razão Social do cliente"
        type: number
      usbDevice:
        description: "Razão Social do cliente"
        type: string
      metricDate:
        description: "Razão Social do cliente"
        type: string
      metricTime:
        description: "Razão Social do cliente"
        type: string
  LoginData:
    type: object
    properties:
      persondata:
        description: "Razão Social do cliente"
        type: string
      password:
        description: "Razão Social do cliente"
        type: string
  SignupData:
    type: object
    properties:
      persondata:
        description: "Razão Social do cliente"
        type: string
      password:
        description: "Razão Social do cliente"
        type: string
      username:
        description: "Razão Social do cliente"
        type: string
  SingupChampionshipData:
    type: object
    properties:
      nameChampionship:
        description: "Razão Social do cliente"
        type: string
      idGame:
        description: "Razão Social do cliente"
        type: integer
  ChampionshipDataList:
    type: array
    items:
      type: object
      properties:
        nmChampionship:
          description: "Razão Social do cliente"
          type: string
        nmGame:
          description: "Razão Social do cliente"
          type: string
        nmCompany:
          description: "Razão Social do cliente"
          type: string
        idChampionship:
          description: "Razão Social do cliente"
          type: integer
  ChampionshipData:
    type: object
    properties:
      nmChampionship:
        description: "Razão Social do cliente"
        type: string
      nmGame:
        description: "Razão Social do cliente"
        type: string
      nmCompany:
        description: "Razão Social do cliente"
        type: string
      idChampionship:
        description: "Razão Social do cliente"
        type: integer
  GameData:
    type: object
    properties:
      idGame:
        description: "Razão Social do cliente"
        type: integer
      nmGame:
        description: "Razão Social do cliente"
        type: string
  GameDataList:
    type: array
    items:
      type: object
      properties:
        idGame:
          description: "Razão Social do cliente"
          type: integer
        nmGame:
          description: "Razão Social do cliente"
          type: string
  InviteCreateData:
    type: object
    properties:
      cpf:
        description: "Razão Social do cliente"
        type: string
  InviteData:
    type: object
    properties:
      idPlayer:
        description: "Razão Social do cliente"
        type: integer
      idChampionship:
        description: "Razão Social do cliente"
        type: integer
      nmChampionship:
        description: "Razão Social do cliente"
        type: string
      idOwner:
        description: "Razão Social do cliente"
        type: integer
      nmGame:
        description: "Razão Social do cliente"
        type: string
  InviteDataList:
    type: array
    items:
      type: object
      properties:
        idPlayer:
          description: "Razão Social do cliente"
          type: integer
        idChampionship:
          description: "Razão Social do cliente"
          type: integer
        nmChampionship:
          description: "Razão Social do cliente"
          type: string
        idOwner:
          description: "Razão Social do cliente"
          type: integer
        nmGame:
          description: "Razão Social do cliente"
          type: string
  MachineDataList:
    type: array
    items:
      type: object
      properties:
        motherBoard:
          description: "Razão Social do cliente"
          type: string
        os:
          description: "Razão Social do cliente"
          type: string
        manufacturer:
          description: "Razão Social do cliente"
          type: string
        model:
          description: "Razão Social do cliente"
          type: string
        idMachine:
          description: "Razão Social do cliente"
          type: integer
  MachineData:
    type: object
    properties:
      motherBoard:
        description: "Razão Social do cliente"
        type: string
      os:
        description: "Razão Social do cliente"
        type: string
      manufacturer:
        description: "Razão Social do cliente"
        type: string
      model:
        description: "Razão Social do cliente"
        type: string
      idMachine:
        description: "Razão Social do cliente"
        type: integer
  MachineMetricData:
    type: object
    properties:
      useRam:
        description: "Razão Social do cliente"
        type: number
      tempGPU:
        description: "Razão Social do cliente"
        type: number
      useGPU:
        description: "Razão Social do cliente"
        type: number
      useCPU:
        description: "Razão Social do cliente"
        type: number
      useDisc:
        description: "Razão Social do cliente"
        type: number
      rpmCooler:
        description: "Razão Social do cliente"
        type: integer
      tempCPU:
        description: "Razão Social do cliente"
        type: number
      usbDevice:
        description: "Razão Social do cliente"
        type: string
      metricDate:
        description: "Razão Social do cliente"
        type: string
      metricTime:
        description: "Razão Social do cliente"
        type: string
      idMachine:
        description: "Razão Social do cliente"
        type: integer
      idMachineMetric:
        description: "Razão Social do cliente"
        type: integer
  MachineMetricDataList:
    type: array
    items:
      type: object
      properties:
        useRam:
          description: "Razão Social do cliente"
          type: number
        tempGPU:
          description: "Razão Social do cliente"
          type: number
        useGPU:
          description: "Razão Social do cliente"
          type: number
        useCPU:
          description: "Razão Social do cliente"
          type: number
        useDisc:
          description: "Razão Social do cliente"
          type: number
        rpmCooler:
          description: "Razão Social do cliente"
          type: integer
        tempCPU:
          description: "Razão Social do cliente"
          type: number
        usbDevice:
          description: "Razão Social do cliente"
          type: string
        metricDate:
          description: "Razão Social do cliente"
          type: string
        metricTime:
          description: "Razão Social do cliente"
          type: string
        idMachine:
          description: "Razão Social do cliente"
          type: integer
        idMachineMetric:
          description: "Razão Social do cliente"
          type: integer
  PlayerChampionshipData:
    type: object
    properties:
      nmPlayer:
        description: "Razão Social do cliente"
        type: string
      idUserRole:
        description: "Razão Social do cliente"
        type: integer
      idChampionship:
        description: "Razão Social do cliente"
        type: integer
  PlayerChampionshipDataList:
    type: array
    items:
      type: object
      properties:
        nmPlayer:
          description: "Razão Social do cliente"
          type: string
        idUserRole:
          description: "Razão Social do cliente"
          type: integer
        idChampionship:
          description: "Razão Social do cliente"
          type: integer
  UserData:
    type: object
    properties:
      idUser:
        description: "Razão Social do cliente"
        type: string
      username:
        description: "Razão Social do cliente"
        type: string
      roles:
        description: "Razão Social do cliente"
        type: array
        items:
          type: integer
      idUserRole:
        description: "Razão Social do cliente"
        type: integer
  InviteTotalData:
    type: object
    properties:
      idPlayer:
        description: "Razão Social do cliente"
        type: string
      idChampionship:
        description: "Razão Social do cliente"
        type: integer
      alreadyAnswered:
        description: "Razão Social do cliente"
        type: integer
      accepted:
        description: "Razão Social do cliente"
        type: integer
  InviteTotalDataList:
    type: array
    items:
      type: object
      properties:
        idPlayer:
          description: "Razão Social do cliente"
          type: string
        idChampionship:
          description: "Razão Social do cliente"
          type: integer
        alreadyAnswered:
          description: "Razão Social do cliente"
          type: integer
        accepted:
          description: "Razão Social do cliente"
          type: integer
*/