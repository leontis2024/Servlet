FROM maven:eclipse-temurin AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM tomcat:9.0.73-jdk11

COPY --from=build /app/target/demoeden-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/demoeden-1.0-SNAPSHOT.war

EXPOSE 8080