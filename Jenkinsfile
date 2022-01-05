pipeline {
   agent any
   tools {
        maven 'Maven 3.8.4'
    }
   stages {
       stage ('Initialize') {
           steps {
               sh '''
                   echo "PATH = ${PATH}"
                   echo "M2_HOME = ${M2_HOME}"
               '''
          }
        }
        stage ('Clean') {
           steps {
                echo "Cleaning up...."
                sh 'mvn clean'
           }
        }
       stage('Build') {
           steps {
              echo 'Building...'
              echo "Running ${env.BUILD_ID} ${env.BUILD_DISPLAY_NAME} on ${env.NODE_NAME} and JOB ${env.JOB_NAME}"
              withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'Sonar_Token') {
                   sh 'mvn -Dmaven.test.failure.ignore=true install sonar:sonar'
               }

          }
       }
   }
}