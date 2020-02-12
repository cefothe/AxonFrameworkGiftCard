# Gift cart application with Axon Framework

## Used technologies
1. [Spring Boot 2.2.4](https://spring.io/projects/spring-boot)
2. [Axon Framework](https://axoniq.io/)
3. Java 11
4. [Axon Server](https://axoniq.io/)
5. Gradle

## How to run the project 
In order to run this demo project you need to download Axon Server from [here](https://download.axoniq.io/axonserver/AxonServer.zip). 
You can start it with docker as well with:
```
docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver
```
Then you can run `./gradlew bootRun`

## Verify
In order to verify that project works fine you can open [Axon Server Dashboard](http://localhost:8024/)