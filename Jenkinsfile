pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                mvn -B clean install
            }
        }
    }
}
