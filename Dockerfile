FROM eclipse-temurin:26-jdk

WORKDIR /app

COPY Main.java Servidor.java ./

RUN javac Main.java Servidor.java

EXPOSE 8080

CMD ["java", "Main"]
