package org.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

@Mojo(name = "display")
public class DisplayMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project.basedir}", readonly = true)
    private File projectDir;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Project directory: " + projectDir);
        getLog().info("--------------------------------");
        runDirectoryPrinting(projectDir);
    }

    private void runDirectoryPrinting(File directory) throws MojoExecutionException {
        try {
            recursiveDirectoryPrinting(directory.toPath(), "");
        } catch (Exception e) {
            throw new MojoExecutionException("Error while scanning project directory");
        }
    }

    private void recursiveDirectoryPrinting(Path dir, String indent) throws Exception {
        File[] files = getValidatedFiles(dir);
        if (files == null) return;
        Arrays.sort(files, Comparator.comparing(File::isFile).thenComparing(File::getName));
        for (File file : files)
            printFile(file, indent);
    }

    private File[] getValidatedFiles(Path dir) {
        File[] files = dir.toFile().listFiles();
        if (files == null)
            getLog().error("Cannot read directory: " + dir);
        return files;
    }

    private void printFile(File file, String indent) throws Exception {
        String name = file.getName();
        getLog().info(indent + name);
        if (file.isDirectory())
            recursiveDirectoryPrinting(file.toPath(), indent + "  ");
    }

}