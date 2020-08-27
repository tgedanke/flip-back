package com.flippost.Tools.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FlippostTools {

    private FlippostTools() {
        // Do nothing
    }


    public static void saveToFile(final String FILE_PATH, final String CONTENT)
    throws IOException {
        final File FILE = new File(FILE_PATH);
        saveToFile(FILE, CONTENT);
    }

    public static void saveToFile(final File FILE, final String CONTENT)
    throws IOException {
        FileWriter writer = new FileWriter(FILE);
        writer.append(CONTENT);
        writer.flush();
        writer.close();
    }
}
