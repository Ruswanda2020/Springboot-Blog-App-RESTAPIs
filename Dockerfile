FROM eclipse-temurin:21-jdk-alpine
LABEL authors="wandasukabumi2020@gmail.com"
WORKDIR /app
COPY /target/springboot-blog-restApi-0.0.1-SNAPSHOT.jar /app/springboot-blog-restApi.jar
ENTRYPOINT ["java", "-jar", "springboot-blog-restApi.jar"]