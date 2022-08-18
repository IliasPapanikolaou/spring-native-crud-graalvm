# Spring native crud with GraalVM

Swagger:
> http://localhost:8080/swagger-ui/index.html

OpenAPI 3.0
> http://localhost:8080/v3/api-docs/

Actuator
> http://localhost:8080/actuator/health

Build image ~ size 142.87MB and startup time: 0.14 sec
```sh
mvn spring-boot:build-image
```

Create docker container from docker image:
```sh
docker run -d --name crud-graalvm -p 8080:8080 spring-native-crud-graalvm:0.0.1-SNAPSHOT
```