package foo.bar;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.AppliedPlugin;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.gradle.testkit.runner.TaskOutcome.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FooPluginTest {
    @Rule public final TemporaryFolder testProjectDir = new TemporaryFolder();
    private File buildFile;

    @Before
    public void setup() throws IOException {
        buildFile = testProjectDir.newFile("build.gradle");
        String buildFileContent = "plugins {" +
                                  "    id 'foo'" +
                                  "}";
        writeFile(buildFile, buildFileContent);
    }
    
    @Test
    public void foo_has_fooTask() throws IOException {
        BuildResult result = executeBuild("tasks");
        assertEquals(result.task(":tasks").getOutcome(), SUCCESS);
        assertTrue(result.getOutput().contains("fooTask"));
    }
    
    @Test
    public void foo_can_execute_fooTask() throws IOException {
        BuildResult result = executeBuild("fooTask");
        assertEquals(result.task(":fooTask").getOutcome(), UP_TO_DATE);
    }
    
    private BuildResult executeBuild(String... tasks) {
        return GradleRunner.create()
                   .withProjectDir(testProjectDir.getRoot())
                   .withArguments(tasks)
                   .forwardOutput()
                   .withPluginClasspath()
                   .build();
    }
    
    private void writeFile(File destination, String content) throws IOException {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(destination));
            output.write(content);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}
