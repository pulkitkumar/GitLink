package com.pulkit.android.gitlink

sealed class GitPullSpec {
    data class Commit(val commit: String) : GitPullSpec()
    data class Tag(val tag: String) : GitPullSpec()
    data class Branch(val branch: String) : GitPullSpec()
    data class RemoteBranch(val remoteBranch: String): GitPullSpec()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as GitPullSpec

        return when(other) {
            is Commit -> (this is Commit && this.commit == other.commit)
            is Tag -> (this is Tag && this.tag == other.tag)
            is Branch -> (this is Branch && this.branch == other.branch)
            is RemoteBranch -> (this is RemoteBranch && this.remoteBranch == other.remoteBranch)
        }
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}