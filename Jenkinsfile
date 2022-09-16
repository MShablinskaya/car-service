pipeline{
    agent any
    stages{
        stage('Build') {
            steps {
                ah './gradlew clean build'
            }
        }
        stage('SonarQube') {
            steps {
              withSonarQubeEnv('Sonar') {
                sh './gradlew jacocoTestReport sonarqube'
              }
            }
        }
    }
}