package com.pulkit.android.gitlink

class Repo(val name: String) {

    lateinit var type: String
    lateinit var value: String
    lateinit var path: String

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