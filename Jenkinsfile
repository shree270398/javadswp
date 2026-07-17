pipeline {

    agent any

    environment {
        IMAGE_NAME = "shree270398/demo"
        IMAGE_TAG = "v5"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/shree270398/javadswp.git'
            }
        }


        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }


        stage('Docker Build') {
            steps {
                sh '''
                docker build -t $IMAGE_NAME:$IMAGE_TAG .
                '''
            }
        }


        stage('Docker Login & Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {

                    sh '''
                    echo $DOCKER_PASS | docker login \
                    -u $DOCKER_USER \
                    --password-stdin

                    docker push $IMAGE_NAME:$IMAGE_TAG

                    docker logout
                    '''
                }
            }
        }


       stage('Deploy') {
    steps {
        sh '''
        ssh vagrant@192.168.56.10 \
        "docker service update --image shree270398/demo:v5 demo-backend"
        '''
    }
}
    }
}
