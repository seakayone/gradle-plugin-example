package foo.bar

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.springframework.boot.gradle.plugin.SpringBootPlugin

class FooPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.tasks.create("fooTask", FooTask.class)

        project.apply(plugin: SpringBootPlugin)
    }

}

class FooTask extends DefaultTask {

}
