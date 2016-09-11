package foo.bar;

import foo.bar.FooTask;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.AppliedPlugin;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

        assertNotNull(foo);
    }

    @Test
    public void foo_has_fooTask() {
        Task fooTask = project.getTasks().findByName("fooTask");

        assertTrue(fooTask instanceof FooTask);
    }

}
