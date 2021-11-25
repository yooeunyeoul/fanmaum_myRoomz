pipeline {
  agent any
  stages {
    stage('') {
      steps {
        sh '''pipeline {
    options { disableConcurrentBuilds() }
    agent { label \'master\' }
    stages{
        stage(\'clean gradlew\') {
            steps {
                sh \'./gradlew clean -Dorg.gradle.java.home=/usr/lib/jvm/java-11-openjdk-amd64\'
            }
        }
        stage(\'Build\'){
            environment {
                appDistributionReleaseNoteString = "BuildNumber : #${BUILD_NUMBER} \\nBranchName : $BRANCH_NAME"
            }
            parallel{
                stage(\'develop Build\'){
                    when {
                        expression {
                             return BRANCH_NAME =~ \'Develop\'
                        }
                    }
                    stages{
                        stage(\'build\') {
                            steps {
                                sh \'./gradlew --profile --rerun-tasks :app:assembleDevRelease -Dorg.gradle.java.home=/usr/lib/jvm/java-11-openjdk-amd64\'
                            }
                        }
                        stage(\'AppDistribution\') {
                            steps {
                                build job : \'Firebase_App_Distribution\', parameters: [ string( name: \'nodeLabelName\', value: \'master\'), string ( name: \'appFilePath\', value: "${WORKSPACE}/app/build/outputs/apk/dev/release/app-dev-release.apk" ) ,string( name : "appDistributionId", value : "1:387295787237:android:f0fdd4104c542e6b003c0c" ) , string( name : "testGroup", value :"Android_Tester_Dev" ), string ( name : "des_notes", value : appDistributionReleaseNoteString ) ]
                            }
                        }
                    }
                }
                stage(\'QA Build\'){
                    when {
                        expression {
                            return BRANCH_NAME =~ \'Release\'
                        }
                    }
                    stages{
                        stage(\'build\') {
                            steps {
                                sh \'./gradlew --profile --rerun-tasks :app:assembleQARelease -Dorg.gradle.java.home=/usr/lib/jvm/java-11-openjdk-amd64\'
                            }
                        }
                        stage(\'AppDistribution\') {
                            steps {
                                build job : \'Firebase_App_Distribution\', parameters: [ string( name: \'nodeLabelName\', value: \'master\'), string ( name: \'appFilePath\', value: "${WORKSPACE}/app/build/outputs/apk/qa/release/app-qa-release.apk" ) ,string( name : "appDistributionId", value : "1:387295787237:android:15874f57bcedd108003c0c" ) , string( name : "testGroup", value :"Android_Tester_QA" ), string ( name : "des_notes", value : appDistributionReleaseNoteString ) ]
                            }
                        }
                    }
                }
                stage(\'Product Build\'){
                    when { branch \'master_dev\'}
                    stages{
                        stage(\'build\') {
                            stages{
                                stage(\'apk build\'){
                                    steps{
                                        sh \'./gradlew --profile --rerun-tasks :app:assembleProductRelease -Dorg.gradle.java.home=/usr/lib/jvm/java-11-openjdk-amd64\'
                                    }
                                }
                                stage(\'bundle build\'){
                                    steps {
                                        sh \'./gradlew --profile --rerun-tasks :app:bundleProductRelease -Dorg.gradle.java.home=/usr/lib/jvm/java-11-openjdk-amd64\'
                                    }
                                }
                            }
                        }

                        stage(\'release\') {
                            steps {
                                archiveArtifacts artifacts : "app/build/outputs/apk/**/*.apk"
                                archiveArtifacts artifacts : "app/build/outputs/bundle/**/*.aab"
                            }
                        }
                    }
                }
            }
        }
    }
}
'''
        }
      }

    }
  }