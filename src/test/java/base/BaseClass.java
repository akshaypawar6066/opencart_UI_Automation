package base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utility.Log;

public class BaseClass {

	@Parameters({ "browser", "osName", "remoteExecution", "remoteURL" })
	@BeforeMethod(alwaysRun = true)
	public void setup(Method method, 
			@Optional("Chrome") String browser,
			@Optional("Windows") String osName,
			@Optional("false") String remoteExecution, 
			@Optional("http://localhost:4444/ui/") String remoteURL) {
		
		boolean isRemote = Boolean.parseBoolean(remoteExecution);
		DriverFactory.setDriver(browser, isRemote, remoteURL);
		Log.info("Starting Test Case: " + method.getName());
		Log.info("Browser: " + browser + " | OS: " + osName);
	}

	public WebDriver getDriver() {
		return DriverFactory.getDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverFactory.quitDriver();
		Log.info("Closing browser for thread: " + Thread.currentThread().getId());
	}

}
