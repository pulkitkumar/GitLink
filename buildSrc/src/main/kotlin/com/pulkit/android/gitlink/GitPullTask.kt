package com.pulkit.android.gitlink

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class GitPullTask : DefaultTask() {

    lateinit var repoDir: File

    lateinit var repoSpec: GitPullSpec

    @TaskAction
    fun gitPull() {
        if (project.gradle.startParameter.isOffline) {
            return
        } else {
            when (repoSpec) {
                is GitPullSpec.Commit -> pullCommit((repoSpec as GitPullSpec.Commit).commit)
                is GitPullSpec.Tag -> pullTag((repoSpec as GitPullSpec.Tag).tag)
                is GitPullSpec.Branch -> pullBranch((repoSpec as GitPullSpec.Branch).branch)
                is GitPullSpec.RemoteBranch -> pullRemoteBranch((repoSpec as GitPullSpec.RemoteBranch).remoteBranch)
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
                it.commandLine("sh", "-c", "cd ${repoDir.absolutePath} && git checkout $branch && git pull")
            }
        }
    }

    private fun pullTag(tag: String) {
        with(project) {
            exec {
                it.commandLine("sh", "-c", "cd ${repoDir.absolutePath} && git checkout $tag && git pull")
            }
        }
    }

    private fun pullCommit(commit: String) {
        with(project) {
            exec {
                it.commandLine("sh", "-c", "cd ${repoDir.absolutePath} && git checkout $commit && git pull")
            }
        }
    }
}