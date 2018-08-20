package com.pulkit.android.gitlink

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project

class GitLinkPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val repos: NamedDomainObjectContainer<Repo> = project.container(Repo::class.java)
        project.extensions.add(EXTENSION_NAME, repos)
        createTasks(project)
    }

    private fun createTasks(project: Project) {
        val repos = project.extensions.getByName(EXTENSION_NAME) as NamedDomainObjectContainer<Repo>
        repos.forEach<Repo?>{
            project.logger.error("ok1>>>>>>>>")
            project.logger.error(it!!.toString())
            project.logger.error("ok2>>>>>>>>")
            GitLink(it!!, project).execute()
        }
    }

    companion object {
        const val EXTENSION_NAME = "repos"
    }
}

