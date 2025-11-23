package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	protected WebDriver driver;
	
	public RegistrationPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	protected WebElement firstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	protected WebElement lastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	protected WebElement email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	protected WebElement telephoneNo;
	
	@FindBy(xpath = "//input[@id='input-password']")
	protected WebElement password;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	protected WebElement confirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	protected WebElement agree;
	
	@FindBy(xpath = "//input[@value='Continue']")
	protected WebElement continueButton;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	protected WebElement confirmationMessage;
	

}
