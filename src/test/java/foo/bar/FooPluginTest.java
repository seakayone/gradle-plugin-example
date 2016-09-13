package foo.bar;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.AppliedPlugin;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FooPluginTest {

    private Project project;

    @Before
    public void setup() {
        this.project = ProjectBuilder.builder().build();
        this.project.getPluginManager().apply("foo");
    }

    @Test
    public void foo_was_applied() {
        AppliedPlugin foo = project.getPluginManager().findPlugin("foo");

        assertThat(foo).isNotNull();
    }

    @Test
    public void foo_has_fooTask() {
        Task fooTask = project.getTasks().findByName("fooTask");

        assertThat(fooTask instanceof FooTask).isTrue();
    }


    @Test
    public void spring_boot_plugin_was_applied() {
        AppliedPlugin springBoot = project.getPluginManager().findPlugin("spring-boot");

        assertThat(springBoot).isNotNull();
    }

}
