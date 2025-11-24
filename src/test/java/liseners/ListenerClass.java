package liseners;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utility.ConfigReader;
import utility.EmailUtil;
import utility.ExtentReportManager;
import utility.FileUtil;
import utility.Log;
import utility.LogCleaner;
import utility.ReportCleaner;
import utility.ScreenshotUtil;

public class ListenerClass implements ITestListener, ISuiteListener, IRetryAnalyzer {
	
	int retryCount = 0, maxRetryCount = 3;


	private static ExtentReports extent = ExtentReportManager.getInstance();
	ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	// From I TestListner->Will execute before all tests once.
	@Override
	public void onStart(ITestContext context) {
		String browser = System.getProperty("browser");
		String osName = System.getProperty("osName");
		
	    if (browser == null || browser.isEmpty()) {
	        browser = context.getCurrentXmlTest().getParameter("browser");
	    }
	    if (osName == null || osName.isEmpty()) {
	        osName = context.getCurrentXmlTest().getParameter("osName");
	    }
		List<String> groups = context.getCurrentXmlTest().getIncludedGroups();

		extent.setSystemInfo("Browser", browser);
		extent.setSystemInfo("OS", osName);
		extent.setSystemInfo("Groups", String.join(",", groups));
	}

	@Override
	public void onStart(ISuite suite) {
		Log.info("Test Suite started:" + suite.getName());
		LogCleaner.clearLog();
		ReportCleaner.cleanReportsAndScreenshots();
	}

	@Override
	public void onFinish(ISuite suite) {
	    System.out.println("Test Suite Finished: " + suite.getName());
	    extent.flush();
	    /*

	    // Paths of files to attach
	    String reportFolder = System.getProperty("user.dir") + "/reports";
	    String reportPath = FileUtil.getLatestFile(reportFolder, ".html");
	    
	    String logPath = System.getProperty("user.dir") + "/logs/automation.log"; 

	    String[] attachments = { reportPath, logPath };

	    // Send email
	    EmailUtil.sendEmailWithAttachments(
	        "akshaypawar6066@gmail.com", // from
	        ConfigReader.get("app.appPassword"),         // Gmail app password
	        "akshaypawar8369@gmail.com",  // to
	        "Automation Test Suite Report",
	        "Hi Team,\n\nPlease find the attached test execution report and logs.\n\nRegards,\nAutomation Team",
	        attachments
	    );
	    */
	}

	@Override
	public void onTestStart(ITestResult result) {
		String fullTestName= result.getMethod().getTestClass().getName() + "." + result.getMethod().getMethodName();
		ExtentTest extentTest = extent.createTest(fullTestName);
		test.set(extentTest);
		test.get().log(Status.INFO, "Starting Test: " + result.getMethod().getMethodName());
		test.get().assignCategory(result.getMethod().getGroups());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test Passed:" + result.getMethod().getMethodName());
		test.get().assignCategory(result.getMethod().getGroups());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, "Test Case Failed:" + result.getMethod().getMethodName());
		test.get().log(Status.FAIL, result.getThrowable());
		test.get().assignCategory(result.getMethod().getGroups());

		WebDriver driver = base.DriverFactory.getDriver();
		if (driver != null) {
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
			try {
				test.get().addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				Log.info("Unable to attch the Screenshot:" + e.getMessage());
			}
		} else {
			Log.info("⚠ Screenshot skipped: Driver was NULL");
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test Skipped_Skipped:"+result.getMethod().getMethodName());
		test.get().assignCategory(result.getMethod().getGroups());
	}
	
	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			System.out.println("Retrying " + result.getName() + "|Attempt-->" + retryCount);
			return true;
		}
		return false;
	}
	@Override
	public void onFinish(ITestContext context) {
		
	}

}
