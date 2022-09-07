pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('SonarQube Analysis') {
            withSonarQubeEnv() {
              sh "./gradlew sonarqube"
            }
        stage('Test') {
                    steps {
                        echo 'Testing...'
                    }
                }
        stage('Deploy') {
            steps {
                echo 'Deploying checking build trigger...'
            }
        }
    }
}
