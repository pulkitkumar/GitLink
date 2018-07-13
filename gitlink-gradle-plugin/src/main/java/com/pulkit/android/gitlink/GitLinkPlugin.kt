package com.pulkit.android.gitlink

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitLinkPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val productContainer = project.container(GitLink::class.java)

        project.extensions.add("gitlink-repositories", productContainer)
    }
}

