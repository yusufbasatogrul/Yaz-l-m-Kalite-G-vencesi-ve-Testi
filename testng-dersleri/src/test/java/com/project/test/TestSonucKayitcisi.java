package com.project.test;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.FileWriter;
import java.io.IOException;

public class TestSonucKayitcisi implements TestWatcher {

    private static String fileName;

    public static void setFileName(String fileName) {
        TestSonucKayitcisi.fileName = fileName;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logTestResult(context, "SUCCESS");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logTestResult(context, "FAILURE");
    }

    private void logTestResult(ExtensionContext context, String status) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.append(context.getDisplayName())
                    .append(",")
                    .append(status)
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
