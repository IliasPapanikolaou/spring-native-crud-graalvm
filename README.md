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

## Istio impementation - minikube

Check if istio has an external port assigned
```sh
kubectl get svc istio-ingressgateway -n istio-system
```

```sh
export INGRESS_HOST=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].port}')
export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].port}')
export TCP_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="tcp")].port}')
```

Run minikube tunnel:
```sh
minikube tunnel
```

Don't forget to insert host to host file (linux: /etc/hosts, windows: C:\Windows\System32\drivers\etc)
```
127.0.0.1 spring-native-crud.com
```

then create istio gateway and virtual service yaml: see gateway.yml file