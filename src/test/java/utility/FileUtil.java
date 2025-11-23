package utility;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileUtil {

    public static String getLatestFile(String folderPath, String extension) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            return null;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(extension));
        if (files == null || files.length == 0) {
            return null;
        }

        // Sort by last modified descending
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0].getAbsolutePath();
    }
}
