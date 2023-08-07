pipeline {
    agent any
     environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "172.18.0.4:8081"
        NEXUS_REPOSITORY = "maven-repo"
        NEXUS_CREDENTIAL_ID = "nexus_3"
        MAVEN_HOME = tool 'maven1'
         NEXUS_REPO_SNAPSHOTS = 'pom.0.0.1-SNAPSHOT'
         NEXUS_REPO_RELEASES = 'maven-repo'
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
                    
                }
               
            }
        }
        
   
        

      

        stage('Deploy to Nexus Snapshots') {
            steps {
                // Deploy the snapshot artifacts to Nexus
                sh "${MAVEN_HOME}/bin/mvn deploy -Dmaven.deploy.skip=true -Dmaven.test.skip=true -DaltDeploymentRepository=snapshot::default::${NEXUS_URL}repository/${NEXUS_REPO_SNAPSHOTS}/"
            }
        }

         stage('Deploy to Nexus Releases') {
           
            steps {
                // Deploy the release artifacts to Nexus
                sh "${MAVEN_HOME}/bin/mvn deploy -Dmaven.deploy.skip=true -Dmaven.test.skip=true -DaltDeploymentRepository=release::default::${NEXUS_URL}repository/${NEXUS_REPO_RELEASES}/"
            }
        }
        
        
    }

    
}


