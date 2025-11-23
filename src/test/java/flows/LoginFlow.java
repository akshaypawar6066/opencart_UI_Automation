package flows;

import org.openqa.selenium.WebDriver;

import pages.LoginPage;
import utility.WebElementActions;

public class LoginFlow extends LoginPage{
	protected WebDriver driver;
	
	public LoginFlow(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void enterEmailId(String emaiId) {
		WebElementActions.sendKeys(driver, emailTextBox, emaiId, "Emaild");
	}
	
	public void enterPassword(String password) {
		WebElementActions.sendKeys(driver, passwordTextBox, password, "Password");
	}
	
	public void clickOnLoginButton() {
		WebElementActions.click(driver, loginButton, "LoginButton");
	}
	
	public void loginToApplication(String emailId, String password) {
		enterEmailId(emailId);
		enterPassword(password);
		clickOnLoginButton();
	}
		
}
