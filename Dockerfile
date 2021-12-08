FROM openjdk
WORKDIR passport
ADD build/libs/passport_server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar