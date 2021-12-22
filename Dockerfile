FROM maven AS build
ADD . /log4jpwn
WORKDIR /log4jpwn
RUN mvn clean compile assembly:single

FROM gcr.io/distroless/java:11
COPY --from=build /log4jpwn/target/log4jpwn-1.0-SNAPSHOT-jar-with-dependencies.jar /log4jpwn.jar

ENV PWN="CVE-2021-44228"
ENV DB_USERNAME="root"
ENV DB_PASSWORD="this_is_a_secret_password123"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/log4jpwn.jar"]
