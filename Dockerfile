FROM adoptopenjdk/openjdk8:slim

ENV PORT 8080
EXPOSE 8080

COPY target/locations-api.jar /opt/application.jar

WORKDIR /opt

CMD ["sh", "-c", "$AMAZON_PROPS && sleep 45 && java $APPLICATION_ARGS -jar application.jar"]
