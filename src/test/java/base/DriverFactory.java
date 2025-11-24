package base;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigReader;
import utility.WaitUtil;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	// ==========================
	// SET BROWSER DRIVER
	// ==========================
	public static void setDriver(String browserName, boolean isRemote, String remoteURL) {
	    try {
	        if (isRemote) {
	            URL url = new URL(remoteURL); // e.g. "http://localhost:4444"
	            if (browserName.equalsIgnoreCase("chrome")) {
	                driver.set(new RemoteWebDriver(url, getChromeOptions()));
	            } else if (browserName.equalsIgnoreCase("edge")) {
	                driver.set(new RemoteWebDriver(url, getEdgeOptions()));
	            } else if (browserName.equalsIgnoreCase("firefox")) {
	                driver.set(new RemoteWebDriver(url, getFirefoxOptions()));
	            } else {
	                throw new IllegalArgumentException("Unsupported Browser: " + browserName);
	            }
	        } else {
	            // local
	            if (browserName.equalsIgnoreCase("chrome")) {
	                driver.set(new ChromeDriver(getChromeOptions()));
	            } else if (browserName.equalsIgnoreCase("edge")) {
	                driver.set(new EdgeDriver(getEdgeOptions()));
	            } else if (browserName.equalsIgnoreCase("firefox")) {
	                driver.set(new FirefoxDriver(getFirefoxOptions()));
	            } else {
	                throw new IllegalArgumentException("Unsupported Browser: " + browserName);
	            }
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to initialize driver: " + e.getMessage(), e);
	    }

	    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    getDriver().get(ConfigReader.get("app.url"));
	    WaitUtil.waitForPageLoad(getDriver());
	}


	// ==========================
	// CHROME OPTIONS
	// ==========================
	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--remote-allow-origins=*");
		options.setAcceptInsecureCerts(true);
		return options;
	}

	// ==========================
	// EDGE OPTIONS
	// ==========================
	private static EdgeOptions getEdgeOptions() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.setAcceptInsecureCerts(true);
		return options;
	}

	// ==========================
	// FIREFOX OPTIONS
	// ==========================
	private static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.setAcceptInsecureCerts(true);
		return options;
	}

	// ==========================
	// GET DRIVER
	// ==========================
	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void waitForPageLoad() {
	    WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(20));
	    wait.until(webDriver -> ((JavascriptExecutor) webDriver)
	            .executeScript("return document.readyState")
	            .equals("complete"));
	}


	// ==========================
	// QUIT DRIVER
	// ==========================
	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
