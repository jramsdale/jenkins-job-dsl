pipelineJob('job-dsl-pipeline-test-again') {

    properties {
        githubProjectUrl('https://github.com/GoogleCloudPlatform/gke-stateful-applications-demo')
    }

    parameters {
        stringParam('CLUSTER_ZONE', 'us-west1-c', 'The zone to build the test cluster in')
        stringParam('PROJECT_ID', 'pso-helmsman-cicd', 'The project to build the test cluster in')
        stringParam('REGION', 'us-west1', 'The region to build the test cluster in')
    }

    triggers {
        githubPullRequest {
            useGitHubHooks()
            orgWhitelist('H3Lm5m4n')
        }
    }


    definition {
        cpsScm {
            scm {
                git {
                    branch('${sha1}')
                    remote {
                        name('origin')
                        refspec('+refs/pull/${ghprbPullId}/*:refs/remotes/origin/pr/${ghprbPullId}/*')
                        url('https://github.com/GoogleCloudPlatform/gke-stateful-applications-demo.git')
                    }
                }

            }
        }
    }
}