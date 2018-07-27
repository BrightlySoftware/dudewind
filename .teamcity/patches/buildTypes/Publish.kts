package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Publish'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("Publish")
    name = "Publish"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        powerShell {
            name = "NPM Version Patch"
            scriptMode = script {
                content = """npm version patch -m "[CHORE] Bump version %s""""
            }
        }
        powerShell {
            name = "Push"
            scriptMode = script {
                content = """
                    git push master
                    git push --tags
                """.trimIndent()
            }
        }
        powerShell {
            name = "Setup Git Config"
            scriptMode = script {
                content = """
                    git config --global push.default simple
                    git config --global user.email "team.city@dudesoln.com"
                    git config --global user.name "Team City"
                """.trimIndent()
            }
        }
    }

    triggers {
        finishBuildTrigger {
            buildTypeExtId = "AutodudeWebdriver_Build"
        }
    }
}))

