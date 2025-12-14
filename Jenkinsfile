pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh '''
                    docker run --rm -v "$(pwd)":/app -v maven-repo:/root/.m2 -w /app my-maven-git:latest mvn -B clean compile
                '''
            }
        }

        stage('Test') {
            steps {
                sh '''
                    docker run --rm -v "$(pwd)":/app -v maven-repo:/root/.m2 -w /app my-maven-git:latest mvn -B test
                '''
            }
        }

        stage('Package') {
            steps {
                sh '''
                    docker run --rm -v "$(pwd)":/app -v maven-repo:/root/.m2 -w /app my-maven-git:latest mvn -B package -DskipTests
                '''
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, allowEmptyArchive: true
        }
        success {
            echo '✅ Pipeline terminée avec succès!'
        }
        failure {
            echo '❌ Pipeline échouée. Vérifiez les logs.'
        }
    }
}
