package com.carbontower

/*

swagger: "2.0"
info:
  description: "Essa é a documentação da API da Carbon Tower para uso do grupo da faculdade"
  version: "1.0.0"
  title: "Carbon Tower Backend"
host: "localhost:8000"
# basePath: "/product-request"
tags:
- name: "championship"
- name: "data"
- name: "signup"
- name: "login"
- name: "logout"
- name: "machine"
- name: "player"
schemes:
- "http"

paths:
    /login:
      post:
        tags:
        - "login"
        summary: "Página de Login"
        description: "Loga o usuário"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação de favorito"
          required: true
          schema:
            $ref: "#/definitions/LoginData"
        responses:
          200:
            description: "Transação favorito feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Transação favorito feito com sucesso"
    /login/java:
      post:
        tags:
        - "login"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação de favorito"
          required: true
          schema:
            $ref: "#/definitions/LoginData"
        responses:
          200:
            description: "Transação favorito feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Transação favorito feito com sucesso"
    /login/logout:
      get:
        tags:
        - "logout"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
    /campeonato/signup:
      post:
        tags:
        - "championship"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação de favorito"
          required: true
          schema:
            $ref: "#/definitions/SingupChampionshipData"
        responses:
          200:
            description: "Transação favorito feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Transação favorito feito com sucesso"
    /campeonato/get:
      get:
        tags:
        - "championship"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/ChampionshipDataList"
    /campeonato/detail/:id:
      get:
        tags:
        - "championship"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/ChampionshipData"
    /campeonato/:id/players:
      get:
        tags:
        - "championship"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/PlayerChampionshipDataList"
    /campeonato/invite/:id/create:
      post:
        tags:
        - "championship"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        consumes:
        - "application/json"
        produces:
        - "application/json"
        parameters:
        - in: "body"
          name: "body"
          description: "Informação de favorito"
          required: true
          schema:
            $ref: "#/definitions/InviteCreateData"
        responses:
          200:
            description: "Transação favorito feito com sucesso"
            schema:
              properties:
                message:
                  type: boolean
                  description: "Transação favorito feito com sucesso"
    /campeonato/games:
      post:
        tags:
        - "championship"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/GameDataList"
    /data/validate:
      get:
        tags:
        - "data"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: string
                  description: "Transação favorito feito com sucesso"
    /data/role:
      get:
        tags:
        - "data"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: string
                  description: "Transação favorito feito com sucesso"
    /data/idrole-empresa:
      get:
        tags:
        - "data"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: integer
                  description: "Transação favorito feito com sucesso"
    /data/idrole-jogador:
      get:
        tags:
        - "data"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: integer
                  description: "Transação favorito feito com sucesso"

    /data/idrole-administrador:
      get:
        tags:
        - "data"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              properties:
                message:
                  type: integer
                  description: "Transação favorito feito com sucesso"
    /data/user:
      get:
        tags:
        - "data"
        summary: "Página de Login"
        description: "Redireciona o usuário para /users/login.html"
        responses:
          200:
            description: OK
            schema:
              $ref: "#/definitions/UserData"
    /machine:
      post:
        tags:
        - "machine"
      get:
        tags:
        - "machine"
    /machine/:id:
      post:
        tags:
        - "machine"
    /machine/metric/:id:
      post:
        tags:
        - "machine"
    /machine/metric/by-date:
      post:
        tags:
        - "machine"
    /player/invites/:idchampionship/accept:
      get:
        tags:
        - "player"
    /player/invites/:idchampionship/refuse:
      get:
        tags:
        - "player"
    /player/invites/get:
      get:
        tags:
        - "player"
    /player/championship:
      get:
        tags:
        - "player"
    /player/administrator:
      get:
        tags:
        - "player"
    /player/administrator/:idchampionship:
      get:
        tags:
        - "player"
    /signup:
      post:
        tags:
        - "signup"

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


 */