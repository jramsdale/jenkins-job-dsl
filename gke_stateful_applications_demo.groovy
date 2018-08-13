pipelineJob('job-dsl-pipeline-test') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/GoogleCloudPlatform/gke-stateful-applications-demo.git')
            }
        }
    }
}