# ktor-betclic-tournament

## Description
REST API to manage the ranking of players in a tournament 

## Install and launch

### - Clone project
```
git clone https://github.com/vdsalexandre/ktor-betclic-tournament.git
```
### - Launch tests
```
./gradlew test
```
### - Build project
```
./gradlew build
```
### - Run project
```
./gradlew run
```

## API usage

| VERB   | URL                                                  | PARAMETER(S)         | COMMENT                                                          |
|--------|------------------------------------------------------|----------------------|------------------------------------------------------------------|
| GET    | http://localhost:9090/tournament/players             |                      | retrieve all players' information                                |
| GET    | http://localhost:9090/tournament/players/sorted      |                      | retrieve all players' information sorted by ranking              |
| GET    | http://localhost:9090/tournament/players/{id}        | **id**: Long         | retrieve player's information based on id player                 |
| GET    | http://localhost:9090/tournament/players/{id}/sorted | **id**: Long         | retrieve player's information and his ranking based on id player |
| POST   | http://localhost:9090/tournament/players             | **nickname**: String | create a new player by giving him his nickname                   |
| PUT    | http://localhost:9090/tournament/players/{id}        | **points**: Long     | update player's points in the tournament                         |
| DELETE | http://localhost:9090/tournament/players/{id}        | **id**: Long         | remove player from the tournament based on id player             |
| DELETE | http://localhost:9090/tournament/players             |                      | remove all players from the tournament                           |