# CalcService

Calculadora como servicio que realiza funciones de sumar y restar

# Parametros
1. data1 -> Primer número de la operación
2. data2 -> Segundo número de la operación
3. task -> Operación a realizar, acepta [add],[substract],[sumar],[restar]

## Programas necesarios

1. Maven (Version 3.8.3 utilizada)
2. Java (Version openjdk version "17.0.1" 2021-10-19)

## Ejecución

1. Descargar el repositorio github -> `https://github.com/CorvenDallas2020/calcservice`
2. Ejecutar instalacion de Maven: -> `mvn clean install`
4. Ejecutar en caso de tener el maven wrapper `./mvnw spring-boot:run`
5. Opcionalmente se puede ejecutar directamente el jar compilado en la carpeta target con `java -jar target/CalcService.jar`
6. Ir a `http://localhost:8080/restful/calculator` añadiendo los parametros necesarios (Ver ejemplo)

Ejemplo `http://localhost:8080/restful/calculator?data1=5&data2=10&task=substract`

Si se arranca por java para poder detener el proceso (no aparece en el administrador de tareas) hay que hacer lo siguiente:

1. En un terminal de windows escribir: jps
2. Localizar el nombre CalcService.jar y ver el numero de proceso que tiene delante
3. En el mismo terminal de windows escribir: taskkill -f /PID {numeroProceso}