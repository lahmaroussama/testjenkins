pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Checkout the source code from your Git repository
                // Replace 'your-git-repo-url' with your actual repository URL
                // You may need to set up credentials to access the repository.
            

                // Set up JDK and Maven in Jenkins Global Tool Configuration.
                // The 'jdk' and 'maven' labels should match the names you configured in Jenkins.
                // 'clean install' will build the project.
                // Replace 'pom.xml' with the actual path to your project's pom.xml file.
                script {
                    def mavenHome = tool 'maven-1'
           
           
                    sh "${mavenHome}/bin/mvn -version"
                    sh "${mavenHome}/bin/mvn clean install -f pom.xml"
                }
            }
        }

        stage('Test') {
            steps {
                // Run unit tests and other test suites
                // Replace 'pom.xml' with the actual path to your project's pom.xml file.
                script {
                    def mavenHome = tool 'maven-1'
                    sh "${mavenHome}/bin/mvn test -f pom.xml"
                }
            }
        }

       stage('SonarQube Analysis') {
            environment {
                // Define SonarQube server details
               
            }
            steps {
                // Run SonarQube analysis using the SonarQube Scanner
                // Make sure you have installed the SonarQube Scanner plugin in Jenkins.
                // Replace 'pom.xml' with the actual path to your project's pom.xml file.
                script {
                    def mavenHome = tool 'maven-1'
                    withSonarQubeEnv('SonarQube') {
                        sh "${mavenHome}/bin/mvn sonar:sonar -Dsonar.host.url=${SONAR_HOST_URL} -Dsonar.login=${SONAR_LOGIN} -f pom.xml"
                    }
                }
            }
        }
    }
    }

   
}
