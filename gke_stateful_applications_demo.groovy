// Change this
PROJECT_NAME = 'gke-stateful-applications-demo'

// You don't need to change these
PROJECT_URL = "https://github.com/GoogleCloudPlatform/${PROJECT_NAME}"
GIT_URL = "${PROJECT_URL}.git"


pipelineJob(PROJECT_NAME) {

    // You don't need to change this
    properties {
        githubProjectUrl(PROJECT_URL)
    }

    // Change these for your pipelines requirements
    parameters {
        // parameter, default value, description
        stringParam('CLUSTER_ZONE', 'us-west1-c', 'The zone to build the test cluster in')
        stringParam('PROJECT_ID', 'pso-helmsman-cicd', 'The project to build the test cluster in')
        stringParam('REGION', 'us-west1', 'The region to build the test cluster in')
        stringParam('sha1', 'master', '')
    }

    // You don't need to change this
    triggers {
        githubPullRequest {
            useGitHubHooks()
            orgWhitelist('GoogleCloudPlatform')
        }
    }

    // You don't need to change this
    definition {
        cpsScm {
            scm {
                git {
                    branch('${sha1}')
                    remote {
                        name('origin')
                        refspec('+refs/pull/${ghprbPullId}/*:refs/remotes/origin/pr/${ghprbPullId}/*')
                        url(GIT_URL)
                    }
                }

            }
        }
    }
}