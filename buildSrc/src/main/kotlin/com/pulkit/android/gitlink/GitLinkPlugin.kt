package com.pulkit.android.gitlink

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class GitLinkPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val repos: NamedDomainObjectContainer<Repo> = project.container(Repo::class.java)
        project.extensions.add(EXTENSION_NAME, repos)
        createTasks(project)
    }

    private fun createTasks(project: Project) {
        val repos = project.extensions.getByName(EXTENSION_NAME) as NamedDomainObjectContainer<Repo>
        repos.all { repo ->
            val taskName = "${repo.name}-git-link-task"

            project.afterEvaluate {
                var spec = makeSpec(repo)
                val task = project.tasks.create(taskName, GitPullTask::class.java)

                with(task) {
                    description = "Pull '${repo.value}' for '${repo.name}'"
                    group = TASK_GROUP_NAME
                    repoDir = File(repo.path)
                    repoSpec = spec!!
                }
                project.tasks.getByName(PRE_BUILD_TASK).dependsOn(task)
            }
        }
    }

    private fun makeSpec(repo: Repo): GitPullSpec? = when (repo.type) {
        COMMIT -> GitPullSpec.Commit(repo.value!!)
        TAG -> GitPullSpec.Tag(repo.value!!)
        BRANCH -> GitPullSpec.Branch(repo.value!!)
        REMOTE_BRANCH -> GitPullSpec.RemoteBranch(repo.value!!)
        else -> null
    }

    companion object {
        const val EXTENSION_NAME = "repos"
        const val COMMIT = "commit"
        const val TAG = "tag"
        const val BRANCH = "branch"
        const val REMOTE_BRANCH = "remoteBranch"
        const val TASK_GROUP_NAME = "Git Pull"
        const val PRE_BUILD_TASK = "preBuild"
    }
}
