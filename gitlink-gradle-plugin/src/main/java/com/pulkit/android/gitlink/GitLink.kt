package com.pulkit.android.gitlink

import org.gradle.api.Project

class GitLink(private val project: Project) {

    lateinit var spec: GitPullSpec

    fun commit(commit: String) {
        spec = GitPullSpec.Commit(commit)
    }

    fun tag(tagName: String) {
        spec = GitPullSpec.Tag(tagName)
    }

    fun branch(branchName: String) {
        spec = GitPullSpec.Branch(branchName)
    }

    fun remoteBranch(branchName: String) {
        spec = GitPullSpec.RemoteBranch(branchName)
    }


    fun execute() {
        assert(spec != null)
        addTasks()
    }

    private fun addTasks() {
        with(project) {
            tasks.create("GIT_PULL_TASK",
                GitPullTask(spec))
        }
    }
}