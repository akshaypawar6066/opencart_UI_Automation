package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	protected WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	protected WebElement emailTextBox;
	
	@FindBy(xpath = "//input[@id='input-password']")
	protected WebElement passwordTextBox;
	
	@FindBy(xpath = "//input[@value='Login']")
	protected WebElement loginButton;
	
}
