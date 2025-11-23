package flows;

import org.openqa.selenium.WebDriver;

import pages.HomePage;
import utility.WebElementActions;

public class HomeFlow extends HomePage {
	protected WebDriver driver;
	
	public HomeFlow(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void clickOnMyAccountLink() {
		WebElementActions.click(driver, myAccountLink, "MyAccountLink");
	}
	
	public void clickOnRegisterLink() {
		WebElementActions.click(driver, registerLink, "Registration Link");
	}
	
	public void clickOnLoginLink() {
		WebElementActions.click(driver, loginLink, "Login Link");
	}

}
