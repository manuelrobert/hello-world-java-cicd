
pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        DOCKER_TAG = getVersion()
    }

    stages{
        stage('SCM') {
            steps{
                git branch: 'main', credentialsId: 'git-cred', url: 'https://github.com/libinmath3w/hello-world-java-cicd.git'
            }
        }
        stage('Maven Build') {
            steps{
               sh "mvn clean package"
            }
        }
        stage('Podman Build') {
            steps{
               sh "podman build . -t libinmathew/hello-world-java:${DOCKER_TAG}"
            }
        }
         stage('Podman push') {
            steps{
                withCredentials([string(credentialsId: 'dockerpass', variable: 'dockhubpwd')]) {
                    sh "podman login -u libinmathew -p ${dockhubpwd}"
                }
               sh "podman push libinmathew/hello-world-java:${DOCKER_TAG}"
            }
        }
          stage('podman Deploy') {
            steps{
               ansiblePlaybook credentialsId: 'jenkins-server', disableHostKeyChecking: true, extras: "-e DOCKER_TAG=${DOCKER_TAG}", installation: 'ansible', inventory: 'dev.inv', playbook: 'deploy-docker.yml'
            }
        }
    }
}
def getVersion() {
    def commitHash = sh returnStdout: true, script: 'git rev-parse --short HEAD'
    return commitHash;
}
