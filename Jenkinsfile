node {
    
    stage "Checkout" {

        deleteDir() 
        checkout scm

        sh "git rev-parse --short HEAD > commit-id"

        tag = readFile('commit-id').replace("\n", "").replace("\r", "")

        appName = "bootdockerproject"

        registryHost = "127.0.0.1:30400/"

        imageName = "${registryHost}${appName}:${tag}"

        env.BUILDIMG=imageName
    }


    stage "Build"

         withMaven(
        // Maven installation declared in the Jenkins "Global Tool Configuration"
        maven: 'mvn',
        // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
        // We recommend to define Maven settings.xml globally at the folder level using 
        // navigating to the folder configuration in the section "Pipeline Maven Configuration / Override global Maven configuration"
        // or globally to the entire master navigating to  "Manage Jenkins / Global Tools Configuration"
        mavenSettingsConfig: 'global-maven-settings') {

      // Run the maven build
      sh 'mvn -version'
      sh "mvn clean package"

    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe & FindBugs & SpotBugs reports...


        sh "docker build -t ${imageName} -f Dockerfile ."
    
    stage "Push"

        sh "docker push ${imageName}"

    stage "Deploy"

        kubernetesDeploy configs: "applications/${appName}/kubernetes/*.yaml", kubeconfigId: 'milind_kubeconfig'

}