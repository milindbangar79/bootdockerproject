FROM openjdk:8-jre
COPY target/docker.jar /app/
RUN chown nobody /app/docker.jar && chmod +x /app/docker.jar
USER 99
EXPOSE 8080
CMD java -jar /app/docker.jar