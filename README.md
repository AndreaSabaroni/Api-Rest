# API Mutantes

## Ambiente de Desarrollo

Descarga de JDK y Eclipse

### Herramientas

* **Java**: JDK 1.11.0-2
* **Gradle**: 4.9
* **Docker**: 18.09.2
* **mssqlServer**: latest version

### Instrucciones para correr localmente

1. Instalar docker
2. Instalar base de datos
    ```bash
    docker pull microsoft/mssql-server-linux
    docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Pa$$w0rd1' -p 1433:1433 -d microsoft/mssql-server-linux:2017-latest
    crear base de datos api_mutantes
    ejecutar flywayMigrate
    ```
3. Correr el proyecto
    ```bash
    ./gradlew bootRun -Dspring.profiles.active=development
    ```

---
