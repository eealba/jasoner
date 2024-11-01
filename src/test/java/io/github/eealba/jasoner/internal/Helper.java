package io.github.eealba.jasoner.internal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Helper {
    static String readResource(String name) throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource(name).toURI());
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }

}
