pipeline {
    agent any 
     environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "172.18.0.4:8081"
        NEXUS_REPOSITORY = "maven-repo"
        NEXUS_CREDENTIAL_ID = "nexus-user"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')

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
        
    
         stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t oussama00001/testjenkins .'
                }
            }
        }
        stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
        stage('Push Docker Image') {
            steps {
                script {
                     sh 'docker push oussama00001/testjenkins'
                    }
                }
            }
        
        stage('Pull Image') {
            steps {
                script {
                    sh 'docker pull oussama00001/testjenkins'
                     sh 'echo "plull succ"'
                }
            }
        }

        stage('Deploy Container') {
            steps {
                script {
                    
                  sh 'docker run -d -p 8082:80 --name my-deployed-container oussama00001/testjenkins'
                    
                }
            }
        }

      
        
     
        

        stage('SonarQube Scanner') {
            steps {
                // Use the specified Maven installation
            withSonarQubeEnv('sq1') {
                 script{
                    def mavenHome = tool 'maven1'
                    sh "${mavenHome}/bin/mvn sonar:sonar"
                 }


                }
            }
        }



       stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
          

    }


}
