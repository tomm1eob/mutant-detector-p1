## Datos:

- **Nombre y Apellido**: Tomás Obredor
- **Comisión**: 3k10
- **Legajo**: 50113

## Enunciado
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma:

```java
 boolean isMutant(String[] dna);
```

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras **más de una secuencia de cuatro letras iguales**, de forma oblicua, horizontal o vertical.
## Instrucciones de cómo ejecutar el programa o la API

En este repositorio se encuentra el proyecto completo con lo requerido en el informe. A continuación se dará instrucciones de como ejecutar el proyecto en distintas situaciones:

#### En local:
1. Se debe ejecutar el archivo `MutantDetectorApplication` que funciona como el `main` de nuestra aplicación.
2. A continuación una vez que en la consola muestre *funcionando* podremos interactuar con la aplicación localmente.
3. Para poder entrar a la consola de H2 usamos `http://localhost:8080/h2-console/`
4. Para entrar al Swagger usamos `http://localhost:8080/swagger-ui/index.html`
5. En la consola estarán las tablas `DNA`, `DNA_AUD`, `REVISION_INFO` totalmente funcionales.
6. En el Swagger se encontraran de manera graficas todos los servicios funcionales. Entre estos estará el requerido llamado mutant.
7. Para poder realizar un post entraremos al servicio `/mutant` y seguiremos este cuerpo de ejemplo.

```Json
{
    "dna": [
      "ATGCGA",
      "CAGTGC",
      "TTATGT",
      "AGAAGG",
      "CCCCTA",
      "TCACTG"
    ]
  }
```
8. Para poder realizarlo en Postman se sigue la misma lógica, se deberá crear una `request` por cada servicio, a continuación se darán las urls:
```
http://localhost:8080/mutant (POST)
http://localhost:8080/find/1 (GET)
http://localhost:8080/all (GET)
http://localhost:8080/delete/1 (DELETE)
http://localhost:8080/stats (GET)
```
> Borrar espacios que hay entre url y tipo de consulta.

#### En Render
En Render seguiremos la misma lógica pero usando la `url` de Render de nuestro deploy `https://mutant-detector-50113.onrender.com`, a continuación daré los pasos a seguir:
1. Para acceder a Swagger usaremos la siguiente URL `https://mutant-detector-50113.onrender.com/swagger-ui/index.html` (esto puede tardar).
2. Una vez cargado nuestro Swagger podremos realizar las consultas mediante los servicios. Para poder realizar un post entraremos al servicio `/mutant` y seguiremos este cuerpo de ejemplo.

```Json
{
    "dna": [
      "ATGCGA",
      "CAGTGC",
      "TTATGT",
      "AGAAGG",
      "CCCCTA",
      "TCACTG"
    ]
  }
```
3. Para poder realizarlo en Postman se sigue la misma lógica, se deberá crear una `request` por cada servicio, a continuación se darán las urls:
```
https://mutant-detector-50113.onrender.com/mutant (POST)
https://mutant-detector-50113.onrender.com/find/1 (GET)
https://mutant-detector-50113.onrender.com/all (GET)
https://mutant-detector-50113.onrender.com/delete/1 (DELETE)
https://mutant-detector-50113.onrender.com/stats (GET)
```
> Borrar espacios que hay entre url y tipo de consulta.

### Aspectos a saber:

[También dentro del proyecto se encuentra el pdf para los documentos solicitados, este estará en la carpeta documents de este repositorio.](documentation/documentación.pdf) <- CLICK AQUI