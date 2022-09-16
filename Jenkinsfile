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
            withSonarQubeEnv() {
              sh "./gradlew sonarqube"
            }
          }
        stage('Deploy') {
            steps {
                echo 'Deploying coming soon..'
            }
        }
    }
}