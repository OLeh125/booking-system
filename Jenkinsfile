pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('build') {
            steps {
                if (params.BUILD_ON == 'gradle') {
                                    echo 'Hello from gradle'
                                    sh 'mvn clean package'
                                } else {
                                    sh 'mvn clean package -DskipTests'
                                }
            }
        }
    }
}