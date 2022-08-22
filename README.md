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

#### GraalVM build error:
Currently, paketobuildpacks/builder-tiny produces an error, use paketobuildpacks/builder:0.1.242-tiny instead.

or 

add the tag: USE_NATIVE_IMAGE_JAVA_PLATFORM_MODULE_SYSTEM=false

```xml
<plugin>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-maven-plugin</artifactId>
<configuration>
  <classifier>${repackage.classifier}</classifier>
  <image>
    <builder>paketobuildpacks/builder:tiny</builder>
    <env>
      <BP_NATIVE_IMAGE>true</BP_NATIVE_IMAGE>
      <USE_NATIVE_IMAGE_JAVA_PLATFORM_MODULE_SYSTEM>false</USE_NATIVE_IMAGE_JAVA_PLATFORM_MODULE_SYSTEM>
    </env>
  </image>
</configuration>
</plugin>
```
