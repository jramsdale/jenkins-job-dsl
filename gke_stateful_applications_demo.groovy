pipelineJob('job-dsl-pipeline-test-again') {

    parameters {
        stringParam('CLUSTER_ZONE', 'us-west1-c', 'The zone to build the test cluster in')
        stringParam('PROJECT_ID', 'pso-helmsman-cicd', 'The project to build the test cluster in')
        stringParam('REGION', 'us-west1', 'The region to build the test cluster in')
    }
    definition {
        cpsScm {
            scm {
                git('https://github.com/GoogleCloudPlatform/gke-stateful-applications-demo.git')
            }
        }
    }
}