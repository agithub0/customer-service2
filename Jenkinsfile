pipeline{
    agent any
    tools {
        maven 'Maven'
    }
    environment {
 		PROJECT_ID = 'round-water-262008'
 		CLUSTER_NAME = 'kubernetes'
         LOCATION = 'us-central1-c'
          CREDENTIALS_ID = 'kubernetes'		
  	}
     stages {
        stage('Build Maven') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github_credentials', url: 'https://github.com/agithub0/customer-service1.git']]])
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                  sh 'docker build -t ashishdocker0/services .'
                }
            }
        }
        stage('Deploy Docker Image') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'ashishdocker0', variable: 'dockerhubpassword')]) {
                    sh 'docker login -u ashishdocker0 -p ${dockerhubpassword}'
                    }
                    sh 'docker push ashishdocker0/services'
                }
            }
        }
        stage('Deploy to K8s') {
		    steps{
			    echo "Deployment of secret started ..."
			    step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'secret.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
				echo "Deployment of config_map started..."
				step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'config_map.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
			    echo "Deployment of database started..."
				step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'db_deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
			    echo "Deployment of application started..."
				step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'app_deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
			    echo "Deployment Finished ..."
		    }
	    }
   }
}
