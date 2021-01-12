properties(
  [
    gitLabConnection('gitlab'),
    buildDiscarder(logRotator(numToKeepStr: '5')),
    disableConcurrentBuilds()
  ]
)

pipeline {
  agent {
    label 'dockerbuilder'
  }
  environment {
    IMAGE = readMavenPom().getArtifactId()
    VERSION = readMavenPom().getVersion()
    IS_SNAPSHOT = readMavenPom().getVersion().endsWith('-SNAPSHOT')
  }

  stages {

    stage('Environment') {
      steps {
        sh label: '', 
        script: '''#!/bin/bash
          echo "echo Value of environment variables:"
          echo "IMAGE: ${IMAGE}"
          echo "VERSION: ${VERSION}"
          echo "IS_SNAPSHOT: ${IS_SNAPSHOT}"
        '''
      }
    }

    stage('Checkout') {
      steps {
        script {
          if (((env.BRANCH_NAME == 'master') && (!env.IS_SNAPSHOT.toBoolean())) || ((env.BRANCH_NAME == 'develop') && (env.IS_SNAPSHOT.toBoolean()))) {
            checkout scm
          } 
          else {
            echo 'I execute on non master or develop branch'
          }
        }
      }
    }

    stage('Build') {
      steps {
        script {
          if (((env.BRANCH_NAME == 'master') && (!env.IS_SNAPSHOT.toBoolean())) || ((env.BRANCH_NAME == 'develop') && (env.IS_SNAPSHOT.toBoolean()))) {
            sh "mvn --settings /tmp/settings.xml clean install -DskipTests -Ddockerfile.skip"
          } 
          else {
            echo 'I execute on non master or develop branch'
          }
        }
      }
    }

    stage('Test') {
      steps {
        script {
          if (((env.BRANCH_NAME == 'master') && (!env.IS_SNAPSHOT.toBoolean())) || ((env.BRANCH_NAME == 'develop') && (env.IS_SNAPSHOT.toBoolean()))) {
            sh "mvn --settings /tmp/settings.xml test -Dspring.profiles.active=jenkins -Ddockerfile.skip"
          } 
          else {
            echo 'I execute on non master or develop branch'
          }
        }
      }
    }

/*
    stage('Sonar') {
      steps {
        script {
          if (((env.BRANCH_NAME == 'master') && (!env.IS_SNAPSHOT.toBoolean())) || ((env.BRANCH_NAME == 'develop') && (env.IS_SNAPSHOT.toBoolean()))) {
            sh label: '', 
            script: '''#!/bin/bash
              mvn --settings /tmp/settings.xml clean install -Dspring.profiles.active=jenkins -DskipTests -Ddockerfile.skip
              mvn --settings /tmp/settings.xml -Dspring.profiles.active=jenkins sonar:sonar -Dsonar.branch=${env.BRANCH_NAME}
            '''
          } 
          else {
            echo 'I execute on non master or develop branch'
          }
        }
      }
    }
*/

    stage('Docker') {
      steps {
        script {
          if ((env.BRANCH_NAME == 'master') && (!env.IS_SNAPSHOT.toBoolean())) {
            sh label: '', 
            script: '''#!/bin/bash
              rm -f target/*stubs.jar
              cp target/*.jar src/main/docker
              mvn --settings /tmp/settings.xml clean install dockerfile:build -DskipTests -Pdocker_master
              mvn --settings /tmp/settings.xml dockerfile:push -DskipTests -Pdocker_master
            '''
          } 
          else if ((env.BRANCH_NAME == 'develop') && (env.IS_SNAPSHOT.toBoolean())) {
            sh label: '', 
            script: '''#!/bin/bash
              rm -f target/*stubs.jar
              cp target/*.jar src/main/docker
              mvn --settings /tmp/settings.xml clean install dockerfile:build -DskipTests -Pdocker_snapshot
              mvn --settings /tmp/settings.xml dockerfile:push -DskipTests -Pdocker_snapshot
            '''
          } 
          else {
            echo 'I execute on non master or develop branch'
          }
        }
      }
    }

    stage('Cleaning') {
      steps {
        script {
          if ((env.BRANCH_NAME == 'master') && (!env.IS_SNAPSHOT.toBoolean())) {
            sh "mvn --settings /tmp/settings.xml -DskipTests docker:removeImage -Pdocker_master"
          } else if ((env.BRANCH_NAME == 'develop') && (env.IS_SNAPSHOT.toBoolean())) {
            sh "mvn --settings /tmp/settings.xml -DskipTests docker:removeImage -Pdocker_snapshot"
          } else {
            echo 'I execute on non master or develop branch'
          }
        }
      }
    }

    stage('Re-Deploy') {
      steps {
        build job: '../re-deploy_docker_back', parameters: [string(name: 'IMAGE', value: "${env.IMAGE}"), string(name: 'VERSION', value: "${env.VERSION}")]
      }
    }

    stage('Clean up our workspace'){
      steps {
        deleteDir()
      }
    }

  }
}
