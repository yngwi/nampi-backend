ARG JAVA_VERSION=11

FROM adoptopenjdk/maven-openjdk${JAVA_VERSION}
LABEL maintainer=“nampi@icar-us.eu”

ARG APPLICATION_PORT=8080
ARG CORE_OWL_URL
ARG DATA_URL
ARG DEFAULT_LIMIT=25
ARG INF_CACHE_URL
ARG KEYCLOAK_REALM
ARG KEYCLOAK_RESOURCE
ARG KEYCLOAK_URL
ARG LOGGING_LEVEL
ARG OTHER_OWL_URLS
ARG PROFILE
ARG REDIS_PORT
ARG REDIS_URL

ENV APPLICATION_PORT=${APPLICATION_PORT}
ENV CORE_OWL_URL=${CORE_OWL_URL}
ENV DATA_URL=${DATA_URL}
ENV DEFAULT_LIMIT=${DEFAULT_LIMIT}
ENV INF_CACHE_URL=${INF_CACHE_URL}
ENV KEYCLOAK_REALM=${KEYCLOAK_REALM}
ENV KEYCLOAK_RESOURCE=${KEYCLOAK_RESOURCE}
ENV KEYCLOAK_URL=${KEYCLOAK_URL}
ENV LOGGING_LEVEL=${LOGGING_LEVEL}
ENV OTHER_OWL_URLS=${OTHER_OWL_URLS}
ENV PROFILE=${PROFILE}
ENV REDIS_PORT=${REDIS_PORT}
ENV REDIS_URL=${REDIS_URL}

RUN curl -fsSL https://deb.nodesource.com/setup_15.x | bash -
RUN apt-get install -y nodejs
RUN npm install -g yarn

EXPOSE ${APPLICATION_PORT}
COPY ./ ./
RUN mvn package -Dmaven.test.skip=true
ENTRYPOINT ["java","-jar","./target/nampi-backend.jar"]
