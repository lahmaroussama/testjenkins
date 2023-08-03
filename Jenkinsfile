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
                script {
                    def mavenHome = tool 'maven1'
                      sh "${mavenHome}/bin/mvn clean package"
                    sh "${mavenHome}/bin/mvn test"
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://172.18.0.3:9000 -Dsonar.login=squ_b3452d6629db6a310d42645b4361740d1e0e8bc9'
                }
               
            }
        }
        
   
       

        stage('SonarQube Scanner') {
            steps {
                // Use the specified Maven installation
            withSonarQubeEnv('sq1') {
                 script {
                    def mavenHome = tool 'maven1'
                    sh "${mavenHome}/bin/mvn clean package sonar:sonar"
                    // Build your Spring Boot application
                    
                 }
                }
            }
        }

       
        
        
    }

    
}


