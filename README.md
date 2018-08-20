# GitLink

## A gradle plugin to checkout a specific tag, branch, remote branch or commit of a dependent git repository.


There are often cases that the codebase, or libraries used in the code are scattered over different git repositories.
With time it often occurs that we need to be switching between tags, branches, commits of the dependent repositories.
To handle such cases I have created the **GitLink** gradle plugin.

Just add the following code into you *build.gradle*. to start using the plugin.

```
apply plugin: 'gitlink'


repos {
    protocolBuffers {
        path "/path/to/local/clone"
        type "branch"
        value "new_ui"
    }
    someLibrary {
        path "/path/to/local/clone"
        type "tag"
        value "2.0"
    }
}
```

We can add any number of repositories with any name in the repos configuration. 

* The current supported **type** are :
1. "tag"
2. "branch"
3. "remoteBranch"
4. "commit"

* **value** is the respective value of the commit, tag, branch, or remote branch, and **path** is the location of the local clone.

The code for the plugin is present in the buildSrc directory.

