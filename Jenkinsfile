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
                    def dockerImage = docker.build("oussama00001/testjenkins:latest")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', DOCKERHUB_CREDENTIALS) {
                        dockerImage.push()
                    }
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
