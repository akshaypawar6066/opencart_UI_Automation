package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver, String testName) {

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String filePath = System.getProperty("user.dir") + "/reports/screenshots/" + testName + "_" + timestamp
				+ ".png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath; // return the screenshot path for attaching
	}
}
