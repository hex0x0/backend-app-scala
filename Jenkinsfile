pipeline {
    agent { docker { image 'sbt:1.2.8' }' }
    stages {
        stage('build') {
            steps {
                sh 'sbt -v'
            }
        }
    }
}