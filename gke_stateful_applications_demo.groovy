pipelineJob('job-dsl-pipeline-test-again') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/GoogleCloudPlatform/gke-stateful-applications-demo.git')
            }
        }
    }
}