package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementActions {

	public static void click(WebDriver driver, WebElement webElement, String elemenetName) {
		try {
			boolean isVisible = WaitUtil.waitForTheElementToBeVisible(driver, webElement, elemenetName);
			if (!isVisible) {
				Log.error("WebElement is Not Visible, Cannot click: " + elemenetName);
				return;
			}
			webElement.click();
			Log.info("Able to click on WebElement:" + elemenetName);

		} catch (Exception e) {
			Log.error("Unable to click on WebElement:" + elemenetName + ".Reason:" + e.getMessage());
		}
	}
	
	public static void sendKeys(WebDriver driver, WebElement webElement, String textToEnter, String elementName) {
		try {
			boolean isVisible = WaitUtil.waitForTheElementToBeVisible(driver, webElement, elementName);
			if(!isVisible) {
				Log.error("Cannot send keys, element NOT visible:" + elementName);
				return;
			}
			webElement.clear();
			webElement.sendKeys(textToEnter.trim());
			Log.info("Entered text '" + textToEnter + "' into: " + elementName);
		}
		catch(Exception e) {
			Log.error("Unable to enter text into WebElement:"+elementName+".Reason:"+e.getMessage());
		}
	}

}
