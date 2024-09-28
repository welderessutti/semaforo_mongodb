#Necessita do executável .jar criado
#FROM eclipse-temurin:21-alpine
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/semaforo_mongodb-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# Cria o executável .jar automaticamente e depois cria a imagem da aplicação
FROM maven:3.9.8-eclipse-temurin-21 AS build
RUN mkdir /opt/app
COPY . /opt/app
WORKDIR /opt/app
RUN mvn clean package

FROM eclipse-temurin:21-jre-alpine
RUN mkdir /opt/app
COPY --from=build  /opt/app/target/app.jar /opt/app/app.jar
WORKDIR /opt/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]