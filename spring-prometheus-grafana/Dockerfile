FROM azul/zulu-openjdk:17
ARG JAR_FILE_PATH=build/libs/prometheus-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]