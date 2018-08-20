package com.pulkit.android.gitlink

class Repo(val name: String) {

    var type: String? = null
    var value: String? = null
    var path: String? = null

    fun type(type: String) {
        this.type = type
    }

    fun value(value: String) {
        this.value = value
    }

    fun path(path: String) {
        this.path = path
    }
}
