package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import base.DriverFactory;
import flows.HomeFlow;
import flows.LoginFlow;
import flows.MyAccountFlow;
import utility.ConfigReader;
import utility.DataProviderClass;


public class LoginTest extends BaseClass {
	
	WebDriver driver;
	
	@Test(enabled = true, groups = {"Sanity"})
	public void verifyLoginFunctionalityOfTheApplicationWithValidCredentials() {
		driver = DriverFactory.getDriver();	
		
		HomeFlow homeflow = new HomeFlow(driver);
		LoginFlow loginFlow = new LoginFlow(driver);
		MyAccountFlow accountFlow = new MyAccountFlow(driver);
		
		homeflow.clickOnMyAccountLink();
		homeflow.clickOnLoginLink();
		loginFlow.loginToApplication(ConfigReader.get("app.emailId"), ConfigReader.get("app.password"));
		accountFlow.verifuIfUserIsLandedOnMyAccountPage();
		
	}
	
	@Test(enabled = true, groups = {"Sanity"})
	public void testLoginForRetry() {
		driver = DriverFactory.getDriver();	
		
		HomeFlow homeflow = new HomeFlow(driver);
		LoginFlow loginFlow = new LoginFlow(driver);
		MyAccountFlow accountFlow = new MyAccountFlow(driver);
		
		homeflow.clickOnMyAccountLink();
		homeflow.clickOnLoginLink();
        Assert.fail();
		
	}
	
	@Test(dataProvider = "InValidLoginData", dataProviderClass = DataProviderClass.class, groups = "DataDriven")
	public void verifyLoginFunctionalityOfTheApplicationWithInValidCredentials(String emailId, String password) {
		driver = DriverFactory.getDriver();	
		
		HomeFlow homeflow = new HomeFlow(driver);
		LoginFlow loginFlow = new LoginFlow(driver);
		MyAccountFlow accountFlow = new MyAccountFlow(driver);
		
		homeflow.clickOnMyAccountLink();
		homeflow.clickOnLoginLink();
		loginFlow.loginToApplication(emailId, password);
		accountFlow.verifuIfUserIsNotAbleToLoginWithInvalidCredentails();
		
	}

}
