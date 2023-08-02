pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from your Git repository
                // Replace 'your-git-repo-url' with your actual repository URL
                // You may need to set up credentials to access the repository.
                checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                          userRemoteConfigs: [[url: 'your-git-repo-url']]])
            }
        }

        stage('Build and Test') {
            steps {
                // Set up JDK and Maven in Jenkins Global Tool Configuration.
                // The 'jdk' and 'maven' labels should match the names you configured in Jenkins.
                // 'clean install' will build the project and run the tests.
                // Replace 'pom.xml' with the actual path to your project's pom.xml file.
                script {
                    def mavenHome = tool 'maven'
                    def jdkHome = tool 'jdk'
                    sh "${jdkHome}/bin/java -version"
                    sh "${mavenHome}/bin/mvn -version"
                    sh "${mavenHome}/bin/mvn clean install -f pom.xml"
                }
            }
        }
    }

    post {
        always {
            // Archive the generated artifact (JAR, WAR, etc.) for future reference
            // Replace 'target/*.jar' with the actual path to your project's generated artifact.
            archiveArtifacts 'target/*.jar'

            // Publish JUnit test results
            // Replace 'target/surefire-reports/*.xml' with the actual path to your test result XML files.
            junit 'target/surefire-reports/*.xml'
        }

        success {
            // Any additional post-build actions you want to perform on successful builds.
        }

        failure {
            // Any additional post-build actions you want to perform on failed builds.
        }
    }
}
