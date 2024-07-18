# Etapa de construcción
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

# Copiar el archivo pom.xml y los archivos de los módulos
COPY pom.xml .
COPY application/pom.xml application/
COPY domain/pom.xml domain/
COPY infrastructure/pom.xml infrastructure/
COPY ui/pom.xml ui/

# Descargar las dependencias
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar la aplicación
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/application/target/application-0.0.1-SNAPSHOT.jar application.jar
COPY --from=build /app/domain/target/domain-0.0.1-SNAPSHOT.jar domain.jar
COPY --from=build /app/infrastructure/target/infrastructure-0.0.1-SNAPSHOT.jar infrastructure.jar
COPY --from=build /app/ui/target/ui-0.0.1-SNAPSHOT.jar ui.jar

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "application.jar"]
