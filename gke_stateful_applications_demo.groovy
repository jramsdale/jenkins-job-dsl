PROJECT_NAME = 'gke-stateful-applications-demo'
PROJECT_URL = "https://github.com/GoogleCloudPlatform/${PROJECT_NAME}"
GIT_URL = "${PROJECT_URL}.git"
// The name of your job in Jenkins
pipelineJob(PROJECT_NAME) {

    // The URL for your project page. Does not include .git
    properties {
        githubProjectUrl(PROJECT_URL)
    }

    // These will likely be different per demo. Change these as needed
    parameters {
        stringParam('CLUSTER_ZONE', 'us-west1-c', 'The zone to build the test cluster in')
        stringParam('PROJECT_ID', 'pso-helmsman-cicd', 'The project to build the test cluster in')
        stringParam('REGION', 'us-west1', 'The region to build the test cluster in')
    }

    // Make sure GoogleCloudPlatform is whitelisted
    triggers {
        githubPullRequest {
            useGitHubHooks()
            orgWhitelist('GoogleCloudPlatform')
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
                        url(GIT_URL)
                    }
                }

            }
        }
    }
}