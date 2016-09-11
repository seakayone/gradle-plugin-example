package foo.bar

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class FooPlugin implements Plugin<Project> {

    void apply(Project prj) {
        prj.tasks.create("fooTask", FooTask.class)
    }

}

class FooTask extends DefaultTask {

}
