# Spring native crud with GraalVM

Endpoint:
> http://localhost:8080/customer

Build image ~ size 142.87MB and startup time: 0.14 sec
```sh
mvn spring-boot:build-image
```

Create docker container from docker image:
```sh
docker run -d --name crud-graalvm -p 8080:8080 spring-native-crud-graalvm:0.0.1-SNAPSHOT
```

PowerShell for loop curl for Kubernetes testing (url changes dynamically):
```shell
for ($i = 0; $i -lt 10000; $i++) { curl http://127.0.0.1:46811/customer }
```