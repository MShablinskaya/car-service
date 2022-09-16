pipeline{
    agent any
    stages{
        stage('Build') {
            steps {
                sh './gradlew clean build'
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