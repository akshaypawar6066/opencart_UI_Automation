package flows;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.MyAccountPage;
import utility.Log;

public class MyAccountFlow extends MyAccountPage {
	protected WebDriver driver;

	public MyAccountFlow(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void verifuIfUserIsLandedOnMyAccountPage() {
		Assert.assertEquals(true, accountPageHedaingMessage.isDisplayed(),
				"User is not able to landed on MyAccountPage:");
		Log.info("User is suceesfully landed on My Account Page:");
	}

	public void verifuIfUserIsNotAbleToLoginWithInvalidCredentails() {

		boolean isDisplayed = false;

		try {
			isDisplayed = accountPageHedaingMessage.isDisplayed();
		} catch (Exception e) {
			Log.info("My Account header NOT found → user is NOT able to login with invalid credentials.");
		}

		Assert.assertFalse(isDisplayed, "User UnExpectedly landed on  My Account Page with Invalid Credentials!");

		Log.info("User Not able to Login with invalid credentials.");
	}

}
