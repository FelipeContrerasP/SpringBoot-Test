# Aplicación  SpringBoot-Test API RESTful creación de usuarios
## Tecnologias utilizadas
- Spring Data JPA
- H2
- SpringBoot 2.2.2
- Devtools
- Spring Web

## Herramientas 
- Spring Tool Suite 4
- Postman

## Compilación y ejecución
 
1. Abrir la terminal en el directorio del proyecto y escribir.
```
./gradlew clean
```
2. Luego ejecutar el siguiente comando Gradle.
```
./gradlew bootRun

```
## Ejemplo de creación de usuario y sus respuestas en Postman

1. Para realizar una petición a la aplicación hay que acceder a la URI localhost:8080/api/usuarios y enviar lo siguiente como json:

```
{
	"name": "Felipe Contreras",
	"email": "fc@gmail.com",
	"password": "Felipe93",
	"phones": [{
		"number": "1234567",
		"citycode": "1",
		"countrycode": "27"
		},
		{
		"number": "242424",
		"citycode": "1",
		"countrycode": "55"
		}]
}
```
Retorna el siguiente json:
```
{
    "usuario": {
        "id": 1,
        "name": "Felipe Contreras",
        "email": "fc@gmail.com",
        "password": "Felipe93",
        "phones": [
            {
                "id": 1,
                "number": 1234567,
                "citycode": 1,
                "countrycode": 27
            },
            {
                "id": 2,
                "number": 242424,
                "citycode": 1,
                "countrycode": 55
            }
        ],
        "created": "2019-12-10T04:12:07.209+0000",
        "modified": "2019-12-10T04:12:07.209+0000",
        "last_login": "2019-12-10T04:12:07.209+0000",
        "token": "b76169dd-ed05-4be0-ba95-1b23fbc1e8b8",
        "active": true
    },
    "mensaje": "El usuario ha sido creado con éxito!"
}
```

En caso de enviar el mismo usuario por postman resulta el siguiente mensaje:
```
{
    "mensaje": "El correo ya está registrado"
}
```
Cumpliendo con el requisito de la aplicación de no repetir email.

2. La aplicacion tambien responde a la restricción de que el correo cumpla con el formato de ejemplo:aaaaaaa@dominio.cl .
En este caso se envia lo siguiente:

```
{
	"name": "Felipe Contreras",
	"email": "fc@gmail",
	"password": "Felipe93"
	
}
```
Retorna:
```
{
    "mensaje": [
        "Error en el campo 'email' :El formato del correo debe ser válido ej: aaaaaaa@dominio.cl"
    ]
}
```
3. Otro caso es que si el password no cumple con el formato (Una Mayuscula, letras minusculas y 2 numeros)
Un ejemplo es el siguiente:
```
{
	"name": "Felipe Contreras Pacheco",
	"email": "fc@gmail.cl",
	"password": "Felipe9"
	
}
```
Devolviendo como respuesta:

```
{
    "mensaje": [
        "Error en el campo 'password' :El formato debe ser correcto(Una Mayuscula, letras minusculas y 2 numeros)"
    ]
}
```
Tambien se válido si estos 2 ultimos casos ocurren al mismo tiempo.











