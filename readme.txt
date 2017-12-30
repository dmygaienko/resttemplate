### DOCKER ###

mvn install -DskipTests dockerfile:build

docker ps

docker run -p 8080:8080 -t springio/resttemplate

docker stop f47cb050f688

https://spring.io/guides/gs/spring-boot-docker/

---------------------------------------------------
### SWAGGER ###

http://localhost:18080/swagger-ui.html
http://localhost:18080/v2/api-docs
---------------------------------------------------