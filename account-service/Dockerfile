FROM openjdk:18
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} account-server.jar
ENTRYPOINT ["java","-jar","/account-server.jar"]
EXPOSE 9001