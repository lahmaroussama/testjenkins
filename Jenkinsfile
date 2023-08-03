pipeline {
    agent any
     environment {
        SONARQUBE_URL = "http://172.17.0.2:9000" // Replace with your SonarQube server URL
        
    }
    stages {
       

        stage('Build and Test') {
            steps {
                // Set up JDK and Maven in Jenkins Global Tool Configuration.
                // The 'jdk' and 'maven' labels should match the names you configured in Jenkins.
                // 'clean install' will build the project and run the tests.
                // Replace 'pom.xml' with the actual path to your project's pom.xml file.
                withMaven(maven: 'maven-1'){
                    sh 'mvn clean package' 
                }  
               
            }
        }
        
   
       

        stage('SonarQube Scanner') {
            steps {
                // Use the specified Maven installation
            withSonarQubeEnv('sq1') {
                 script {
                    def mavenHome = tool 'maven-1'
                    sh "${mavenHome}/bin/mvn clean package sonar:sonar"
                    // Build your Spring Boot application
                    
                 }
                }
            }
        }

       
        
        
    }

    
}


