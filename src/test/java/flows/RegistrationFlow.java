package flows;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.RegistrationPage;
import utility.Log;
import utility.WebElementActions;

public class RegistrationFlow extends RegistrationPage{
	
	protected WebDriver driver;
	
	public RegistrationFlow(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
    public void enterFirstName(String fname) {
      WebElementActions.sendKeys(driver, firstName, fname, "FirstName");
    }

    public void enterLastName(String lname) {
    	 WebElementActions.sendKeys(driver, lastName, lname, "LastName");
    }

    public void enterEmail(String emailId) {
    	 WebElementActions.sendKeys(driver, email, emailId, "EmailId");
    }

    public void enterTelephone(String phone) {
    	 WebElementActions.sendKeys(driver, telephoneNo, phone, "PhoneNo");
    }

    public void enterPassword(String pwd) {
    	 WebElementActions.sendKeys(driver, password, pwd, "Password");
    }

    public void enterConfirmPassword(String confirmPwd) {
    	 WebElementActions.sendKeys(driver, confirmPassword, confirmPwd, "Confrm Password");
    }

    public void clickAgree() {
        if (!agree.isSelected()) {
            WebElementActions.click(driver, agree, "Agree CheckBox");
        }
    }
    
    public void clickOnContinueButton() {
    	  WebElementActions.click(driver, continueButton, "Continue Button");
    }

    public String getConfirmationMessage() {
        try {
        	return (confirmationMessage.getText().trim());
        }
        catch(Exception e) {
        	return (e.toString());
        }
    }
    
    
    public void completeRegistration(String fname,String lname,String emailId,  String phone, String pwd) {

        enterFirstName(fname);
        enterLastName(lname);
        enterEmail(emailId);
        enterTelephone(phone);
        enterPassword(pwd);
        enterConfirmPassword(pwd);
        clickAgree();
        clickOnContinueButton();
    }
    
    public void verifyRegistationIsSuccefullyDoneOrNot(String expectedConformationMessage) {
    	String actualConfirmationMessage = getConfirmationMessage();
    	Assert.assertEquals(actualConfirmationMessage, expectedConformationMessage, "Registartion is not done Correctly.");
    	Log.info("Registration Successful:!!!");
    }
    
}
