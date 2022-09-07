pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './build_and_push.sh'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying checking build trigger...'
            }
        }
    }
}