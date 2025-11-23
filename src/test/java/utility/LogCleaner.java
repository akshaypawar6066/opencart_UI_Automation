package utility;

import java.io.File;
import java.io.PrintWriter;

public class LogCleaner {

    private static final String LOG_FILE_PATH = System.getProperty("user.dir") + "/logs/automation.log";

    // Clear the log file content
    public static void clearLog() {
        File file = new File(LOG_FILE_PATH);
        if (file.exists()) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(""); // Clear the content
                System.out.println("✅ Log file cleared: " + LOG_FILE_PATH);
            } catch (Exception e) {
                System.out.println("⚠ Unable to clear log file: " + LOG_FILE_PATH);
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠ Log file does not exist: " + LOG_FILE_PATH);
        }
    }
}

