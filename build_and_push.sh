./gradlew clean build
docker build . -t mariemacheri/car-service:1.0.0
docker push mariemacheri/car-service:1.0.0