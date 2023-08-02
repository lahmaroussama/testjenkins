pipeline {
    agent any

    stages {
    

        stage('Build and Test') {
            steps {
                // Set up JDK and Maven in Jenkins Global Tool Configuration.
                // The 'jdk' and 'maven' labels should match the names you configured in Jenkins.
                // 'clean install' will build the project and run the tests.
                // Replace 'pom.xml' with the actual path to your project's pom.xml file.
                script {
                    def mavenHome = tool 'maven-1'
                 
                    sh "${mavenHome}/bin/mvn -version"
                    sh "${mavenHome}/bin/mvn clean install -f pom.xml"
                }
            }
        }
         stages {
        // ... other stages remain the same

        stage('Build') {
            steps {
                // Use the specified Maven installation
            withSonarQubeEnv('sq1') {
                    // Build your Spring Boot application
                    sh ' clean sonar:sonar'
                }
            }
        }

       
        
        
    }

    
}


