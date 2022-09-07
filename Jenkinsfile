pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('SonarQube') {
                            steps {
                                echo 'Here will be SonarQube...'
                            }
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
