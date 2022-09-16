pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('SCM') {
            checkout scm
          }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv() {
                              sh "./gradlew sonarqube"
                            }
            }
        }
    }
}