pipeline {
    agent any
    stages {
        stage('Build, Test & Publish') {
            steps {
                sh 'sbt --server "clean; testFull; publishLocal; publish; shutdown"'
            }
        }
    }
}