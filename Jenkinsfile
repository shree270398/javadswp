pipeline {
    agent any

    triggers {
        cron('')
    }

    environment {
        IMAGE_NAME = "shree270398/devcidoc"
        CONTAINER_NAME = "devcidoc-container"
    }

    stages {

        stage('Clone GitHub Repository') {
            steps {
                git branch: 'main',
                url: 'https://github.com/shree270398/devcidoc.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Hub Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push $IMAGE_NAME'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker stop $CONTAINER_NAME || true'
                sh 'docker rm $CONTAINER_NAME || true'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }
    }

    post {

        success {
            echo 'CI/CD Pipeline Executed Successfully!'
        }

        failure {
            echo 'Pipeline Failed!'
        }
    }
}
