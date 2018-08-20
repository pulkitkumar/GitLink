package com.pulkit.android.gitlink

import org.gradle.api.Project
import java.io.File

class GitLink(private val repo: Repo, private val project: Project) {

    fun execute() {
        var spec = makeSpec()
        spec?.let {
            with(project) {
                val taskName = "${repo.name}-git-link-task"
                val task = tasks.create(taskName, GitPullTask::class.java) {
                    with(it) {
                        description = "Pull '${repo.value}' for '${repo.name}'"
                        group = TASK_GROUP_NAME
                        repoDir = File(repo.path)
                        repoSpec = spec
                    }
                }
                tasks.getByName(PRE_BUILD_TASK).dependsOn(task)
            }
        }
    }

    private fun makeSpec(): GitPullSpec? = when (repo.type) {
        COMMIT -> GitPullSpec.Commit(repo.value)
        TAG -> GitPullSpec.Tag(repo.value)
        BRANCH -> GitPullSpec.Branch(repo.value)
        REMOTE_BRANCH -> GitPullSpec.RemoteBranch(repo.value)
        else -> null
    }


    companion object {
        const val COMMIT = "commit"
        const val TAG = "tag"
        const val BRANCH = "branch"
        const val REMOTE_BRANCH = "remoteBranch"
        const val TASK_GROUP_NAME = "Git Pull"
        const val PRE_BUILD_TASK = "preBuild"
    }
}