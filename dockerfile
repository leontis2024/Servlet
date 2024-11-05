# Fase 1: Build do projeto com JDK 21
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Fase 2: Execução com Tomcat 10.1 e JDK 21
FROM eclipse-temurin:21-jre

# Definindo a versão do Tomcat
ENV TOMCAT_VERSION=10.1.19

# Instalando o Tomcat
RUN curl -O https://dlcdn.apache.org/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar xzf apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    mv apache-tomcat-${TOMCAT_VERSION} /usr/local/tomcat

# Copiando o WAR para o Tomcat
COPY --from=build /app/target/Leontis-Servlet-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

# Expondo a porta
EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
