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
                    def mavenHome = tool 'maven1'
                      sh "${mavenHome}/bin/mvn clean package"
                    sh "${mavenHome}/bin/mvn test"
                    
                }
               
            }
        }
        
   
       

        stage('SonarQube Scanner') {
            steps {
                // Use the specified Maven installation
            withSonarQubeEnv('sq1') {
                 script{
                    def mavenHome = tool 'maven1'
                    sh "${mavenHome}/bin/mvn sonar:sonar -X"
                
                    sh '${mavenHome}/bin/mvn sonar:sonar -Dsonar.host.url=http://172.17.0.2:9000 -Dsonar.login=squ_0943eae08f5cdc8352c1cbfa456fc6814e4bdddf'
                 }
                }
            }
        }

       
        
        
    }

    
}


