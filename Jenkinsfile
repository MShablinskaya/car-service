pipeline {
    agent any
    environment {
        BITBUCKET_COMMON_CREDS = credentials('bitbucket-ssh-key')
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }
        stage('Test') {
            steps {
                'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }
}