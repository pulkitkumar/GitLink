package com.pulkit.android.gitlink

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

class GitPullTask constructor(@Input val repoSpec: GitPullSpec): DefaultTask() {

    @OutputDirectory
    lateinit var repoDir: File

    private var isOffline: Boolean = false
        get() = project.gradle.startParameter.isOffline

    @TaskAction
    fun gitPull() {
        if (isOffline) {
            return
        } else {
            when (repoSpec) {
                is GitPullSpec.Commit -> pullCommit(repoSpec.commit)
                is GitPullSpec.Tag -> pullTag(repoSpec.tag)
                is GitPullSpec.Branch -> pullBranch(repoSpec.branch)
                is GitPullSpec.RemoteBranch -> pullRemoteBranch(repoSpec.remoteBranch)
            }
        }
    }

    private fun pullRemoteBranch(remoteBranch: String) {
        with(project) {
            exec {
                it.commandLine("sh", "-c", "cd ${repoDir.absolutePath} && git fetch && git checkout $remoteBranch")
            }
        }
    }

    private fun pullBranch(branch: String) {
        with(project) {
          exec {
              it.commandLine("sh", "-c", "cd ${repoDir.absolutePath} && git checkout $branch && git pull" )
          }
        }
    }

    private fun pullTag(tag: String) {
        with(project) {
            exec {
                it.commandLine("sh", "-c", "cd ${repoDir.absolutePath} && git checkout $ && git pull" )
            }
        }
    }

    private fun pullCommit(commit: String) {
        with(project) {
            exec {
                it.commandLine("sh", "-c", "cd ${repoDir.absolutePath} && git checkout $ && git pull" )
            }
        }
    }
}