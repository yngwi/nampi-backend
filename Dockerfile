ARG JAVA_VERSION=11

FROM adoptopenjdk/maven-openjdk${JAVA_VERSION}
LABEL maintainer=“nampi@icar-us.eu”

ARG APPLICATION_PORT=8080
ARG DEFAULT_LIMIT=25
ARG KEYCLOAK_REALM
ARG KEYCLOAK_RESOURCE
ARG KEYCLOAK_URL
ARG LOGGING_LEVEL
ARG OTHER_OWL_URLS
ARG REDIS_URL
ARG REDIS_PORT
ARG TRIPLE_STORE_URL

ENV APPLICATION_PORT=${APPLICATION_PORT}
ENV DEFAULT_LIMIT=${DEFAULT_LIMIT}
ENV KEYCLOAK_REALM=${KEYCLOAK_REALM}
ENV KEYCLOAK_RESOURCE=${KEYCLOAK_RESOURCE}
ENV KEYCLOAK_URL=${KEYCLOAK_URL}
ENV LOGGING_LEVEL=${LOGGING_LEVEL}
ENV OTHER_OWL_URLS=${OTHER_OWL_URLS}
ENV REDIS_URL=${REDIS_URL}
ENV REDIS_PORT=${REDIS_PORT}
ENV TRIPLE_STORE_URL=${TRIPLE_STORE_URL}

EXPOSE ${APPLICATION_PORT}
COPY ./ ./
RUN mvn package
ENTRYPOINT ["java","-jar","./target/nampi-backend.jar"]