package de.jonasmetzger.kip2;

import de.jonasmetzger.kip1.Client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Utils {

    public static String load(String str) {
        try {
            return Files.readString(Path.of(Objects.requireNonNull(Client.class.getResource("/" + str)).toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }
}
