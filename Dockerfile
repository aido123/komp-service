FROM java:8-jdk

ARG JAR_FILE=target/*.jar

WORKDIR /tmp

COPY ${JAR_FILE} /tmp/app.jar

RUN curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
RUN chmod +x /kubectl
RUN mv ./kubectl /usr/local/bin/kubectl

CMD["java", "-jar", "/tmp/app.jar"]

EXPOSE 8080

USER 101