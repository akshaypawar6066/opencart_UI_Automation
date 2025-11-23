package utility;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitUtil {

	private static Duration timeout = Duration.ofSeconds(20);

	public static void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}
	public static boolean waitForTheElementToBeVisible(WebDriver driver, WebElement webElement, String elementName) {
	    try {
	        FluentWait<WebDriver> wait = new FluentWait<>(driver)
	                .withTimeout(timeout)
	                .pollingEvery(Duration.ofSeconds(2))
	                .ignoring(NoSuchElementException.class)
	                .ignoring(StaleElementReferenceException.class);

	        wait.until(ExpectedConditions.visibilityOf(webElement));
	        Log.info("WebElement is Visible: " + elementName);
	        return true;

	    } catch (Exception e) {
	        String message = "WebElement is NOT Visible: " + elementName + 
	                         ". Reason: " + e.getMessage();

	        Log.error(message);
	        Assert.fail(message);       // Fail with reason
	        return false;               // Required by Java (but won't execute)
	    }
	}

}
