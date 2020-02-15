pipeline {
    agent { docker { image 'hseeberger/scala-sbt:graalvm-ce-19.3.0-java11_1.3.8_2.12.10' } }
    stages {
        stage('build') {
            steps {
                sh 'sbt -v'
            }
        }
    }
}