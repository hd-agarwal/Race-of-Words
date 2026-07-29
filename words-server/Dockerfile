FROM eclipse-temurin:25-jdk-jammy AS builder

WORKDIR /build

RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    && rm -rf /var/lib/apt/lists/*

RUN curl -L https://services.gradle.org/distributions/gradle-9.5.1-bin.zip -o gradle-9.5.1-bin.zip
RUN apt-get install -y unzip
RUN unzip gradle-9.5.1-bin.zip
ENV GRADLE_HOME=/build/gradle-9.5.1
ENV PATH=$PATH:$GRADLE_HOME/bin

COPY settings.gradle build.gradle ./
RUN gradle build -x test --no-daemon || true
COPY src ./src
RUN gradle build -x test --no-daemon


FROM eclipse-temurin:25-jre-jammy
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*
RUN groupadd -r appuser && useradd -r -g appuser -d /app appuser
WORKDIR /app
RUN mkdir -p /app/logs && chown -R appuser:appuser /app/logs
USER appuser
COPY --from=builder /build/build/libs/*.jar ./app.jar
ENTRYPOINT ["java" , "-jar", "app.jar"]
