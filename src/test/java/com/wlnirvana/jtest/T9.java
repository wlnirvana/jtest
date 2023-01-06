package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class T9 {

    /**
     * Check if the packaged jtest.jar works
     */
    @Test
    void jarWorks() throws IOException, InterruptedException {
        // java in PATH
        var rt = Runtime.getRuntime();
        var proc = rt.exec("javac -version");
        proc.waitFor(5, TimeUnit.SECONDS);
        assertEquals(0, proc.exitValue(), "Your javac is not in PATH");

        // can compile ToyTest
        var jarPath = Paths.get("src", "test", "resources", "jtest.jar").toRealPath().toString();
        var resourcesPath = Paths.get("src", "test", "resources").toRealPath().toString();
        var toyTestPath = Paths.get("src", "test", "resources", "ToyTest.java").toRealPath().toString();

        String[] compileCommand = {"javac", "-cp", jarPath, toyTestPath};
        proc = rt.exec(compileCommand);
        proc.waitFor(5, TimeUnit.SECONDS);
        assertEquals(0, proc.exitValue());

        // can run ToyTest
        String runClasspath;
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            runClasspath = String.join(";", List.of(jarPath, resourcesPath));
        } else {
            runClasspath = String.join(":", List.of(jarPath, resourcesPath));
        }

        String[] runCommand = {"java", "-cp", runClasspath, "com.wlnirvana.jtest.JTestRunner", "ToyTest"};
        proc = rt.exec(runCommand);
        proc.waitFor(5, TimeUnit.SECONDS);
        assertEquals(0, proc.exitValue());

        // output of ToyTest is correct
        var output = new String(proc.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        assertTrue(output.contains("1/3 have passed"));
        assertTrue(output.contains("Failed tests are: [assertTrueFail]"));
        assertTrue(output.contains("Errored tests are: [shouldError]"));
    }
}