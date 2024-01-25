# Spirit-Island-Tracker-API

## Decription
This is a Spring Boot Rest API used for the application spirit island tracker. This keeps track of a user's game session
and its details.

## Endpoints

All endpoints are prefixed with /api/v1

|  HTTP Method  |    Endpoint   |  Description |
| ------------- | ------------- | -------|
| GET  | /spirits | Retrieve all Spirits
| GET  | /spirits/{id}  | Retrieve a Spirit by id
| GET  | /adversaries | Retrieve all adversaries
| GET  | /adversaries/{id} | Retrieve an adversary by id
| GET  | /users  | Retrieve all users
| GET  | /users/{id} | Retrieve a user by id
| GET  | /game-sessions | Retrieve all game sessions
| GET  | /game-sessions/{id} | Retrieve a game session by id
| POST | /users/register | Register a new user
| POST | /game-sessions | Create a new game session
| PATCH | /users/update-username/{id} | Update an existing user's username
| PATCH | /users/update-password/{id} | Update an existing user's password
| PUT | /game-sessions/{id} | Update an exsting game session's data
| DELETE | /users/{id} | Delete a user by id
| DELETE | /game-sessions/{id} | Delete a game session by id
