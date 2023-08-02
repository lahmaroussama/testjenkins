pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Build the Spring Boot application using Maven
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Run JUnit tests and generate test reports
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis using SonarQube Scanner
                withSonarQubeEnv('sq1') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }

    post {
        always {
            // Archive the generated JUnit test reports
            junit 'target/surefire-reports/**/*.xml'
        }
    }
}

