// Change this
PROJECT_NAME = 'gke-rolling-updates-demo'

// You don't need to change these
PROJECT_URL = "https://github.com/GoogleCloudPlatform/${PROJECT_NAME}"
GIT_URL = "${PROJECT_URL}.git"


pipelineJob(PROJECT_NAME) {

    // You don't need to change this
    properties {
        githubProjectUrl(PROJECT_URL)
    }

    // CHANGE THESE for your pipelines requirements
    parameters {
        // parameter, default value, description
        stringParam('CLUSTER_ZONE', 'us-west1-c', 'The zone to build the test cluster in')
        stringParam('PROJECT_ID', 'pso-helmsman-cicd', 'The project to build the test cluster in')
        stringParam('REGION', 'us-west1', 'The region to build the test cluster in')
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
