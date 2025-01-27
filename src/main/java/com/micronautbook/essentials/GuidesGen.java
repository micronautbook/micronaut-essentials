package com.micronautbook.essentials;

import io.micronaut.configuration.picocli.PicocliRunner;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import io.micronaut.guides.core.WebsiteGenerator;

import java.io.File;
import java.io.IOException;

@Command(name = "guides-gen", description = "...",
        mixinStandardHelpOptions = true)
public class GuidesGen implements Runnable {

    @Option(names = {"-i", "--input"}, description = "folder contain the tutorials", required = true)
    File input;

    @Option(names = {"-o", "--output"}, description = "folder where the website should be generated into", required = true)
    File output;

    @Inject
    WebsiteGenerator websiteGenerator;

    public static void main(String[] args) {
        PicocliRunner.run(GuidesGen.class, args);
    }

    public void run() {
        try {
            websiteGenerator.generate(input, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
