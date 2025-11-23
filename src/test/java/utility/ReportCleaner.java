package utility;

import java.io.File;

public class ReportCleaner {

    private static final String REPORTS_FOLDER = System.getProperty("user.dir") + "/reports";
    private static final String SCREENSHOTS_FOLDER = REPORTS_FOLDER + "/screenshots";

    public static void cleanReportsAndScreenshots() {
        deleteFolderContents(REPORTS_FOLDER);
        deleteFolderContents(SCREENSHOTS_FOLDER);
        System.out.println("✅ Reports and Screenshots cleaned!");
    }

    private static void deleteFolderContents(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isDirectory() && file.delete()) {
                        System.out.println("Deleted: " + file.getName());
                    }
                }
            }
        }
    }
}
