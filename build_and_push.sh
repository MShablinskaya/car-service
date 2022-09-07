./gradlew clean build
docker build . -t mshablinskaya/car-service:1.0.0
docker push mshablinskaya/car-service:1.0.0