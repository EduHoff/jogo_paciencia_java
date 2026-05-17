FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY src ./src

CMD sh -c 'javac -d bin $(find src -name "*.java") && java -cp bin application.Program'