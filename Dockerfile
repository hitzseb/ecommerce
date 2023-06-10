FROM amazoncorretto:17
MAINTAINER hitzseb
COPY target/ecommerce-0.0.1-SNAPSHOT.jar ecommerce
ENTRYPOINT ["java", "-jar","ecommerce"]
EXPOSE 8000