## Registrar usuario

Endpoint

    http://localhost:8080/register

Request:

        {
        "username":"admin",
        "password":"admin",
        "role":"ADMIN"}

Response:

        {
            "username": "admin",
            "password": "$2a$10$TJtd9DTtUblE7gzNGt83SOwVJdpkWGLkWwU5d6PJVS9ofMHSvyq0m",
            "role": "ADMIN"
        }

## Auth

        localhost:8080/authenticate

Request:

        {
        "username":"user2",
        "password":"user2"
        }

        localhost:8080/authenticate

Ejemplo de respuesta:

    {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsIlVzZXJEZXRhaWxzIjp7InBhc3N3b3JkIjoiJDJhJDEwJFRKdGQ5RFR0VWJsRTdnek5HdDgzU093VkpkcGtXR0xrV3dVNWQ2UEpWUzlvZk1IU3Z5cTBtIiwidXNlcm5hbWUiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJBRE1JTiJ9XSwiYWNjb3VudE5vbkV4cGlyZWQiOnRydWUsImFjY291bnROb25Mb2NrZWQiOnRydWUsImNyZWRlbnRpYWxzTm9uRXhwaXJlZCI6dHJ1ZSwiZW5hYmxlZCI6dHJ1ZX0sImlzQWRtaW4iOnRydWUsImV4cCI6MTYyOTI4NjMyMCwiaWF0IjoxNjI5MjY4MzIwfQ.-UWY2dgItWzlgS3a7WbKcFONNn5FKwEnQ_REE9jDbtcEuFlR6S5Y6ANar64vh7kjQZdzmzaWMXPVQO0klmN4cA"
    }

## HelloWorld

Endpoint para utilizar como test para la autentificación de los diferentes tipos de usuarios.

localhost:8080/hello_admin

Base de datos desplegada en contenedor docker en local.

Parametros

Authorization

    BearerToken

Request

    curl --location --request GET 'localhost:8080/hello_admin' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsIlVzZXJEZXRhaWxzIjp7InBhc3N3b3JkIjoiJDJhJDEwJFRKdGQ5RFR0VWJsRTdnek5HdDgzU093VkpkcGtXR0xrV3dVNWQ2UEpWUzlvZk1IU3Z5cTBtIiwidXNlcm5hbWUiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJBRE1JTiJ9XSwiYWNjb3VudE5vbkV4cGlyZWQiOnRydWUsImFjY291bnROb25Mb2NrZWQiOnRydWUsImNyZWRlbnRpYWxzTm9uRXhwaXJlZCI6dHJ1ZSwiZW5hYmxlZCI6dHJ1ZX0sImlzQWRtaW4iOnRydWUsImV4cCI6MTYyOTI4NjMyMCwiaWF0IjoxNjI5MjY4MzIwfQ.-UWY2dgItWzlgS3a7WbKcFONNn5FKwEnQ_REE9jDbtcEuFlR6S5Y6ANar64vh7kjQZdzmzaWMXPVQO0klmN4cA'

Response

    Hello Admin

![img.png](img.png)

### Errores

Response 403

    {
        "timestamp": "2021-08-17T12:25:18.408+0000",
        "status": 403,
        "error": "Forbidden",
        "message": "Forbidden",
        "path": "/hello_admin"
    }

Token Expirado

    {
        "error": "JWT expired at 2021-08-17T20:02:09Z. Current time: 2021-08-18T08:32:24Z, a difference of 45015746 milliseconds.  Allowed clock skew: 0 milliseconds."
    }

## Endpoints


###

POST http://localhost:3333/purchase-ticket

###
DEPRECATED
POST http://localhost:4444/addUser

###

DELETE http://localhost:4444/deleteUser/{{idUsuario}}

###

GET http://localhost:4444/listUserById/{{idUsuario}}

###

GET http://localhost:4444/listUsers

###

PUT http://localhost:4444/modifyUser

###

POST http://localhost:3333/kafka/msg

###

POST http://localhost:3333/purchase-ticket
    
