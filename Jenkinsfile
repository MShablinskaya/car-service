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
   post {
         always {
           emailext(
           subject: "Jenkins: ${env.JOB_NAME} ${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
           body: """<p>Build status is ${currentBuild.currentResult}. Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>;</p>""",
           recipientProviders: [buildUser(), developers(), requestor()],
           )
           junit 'build/test-results/**/*.xml'
         }
       }
     tools {
       jdk 'JDK_17_new'
     }
   }
}