package tests;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;
import base.DriverFactory;
import flows.HomeFlow;
import flows.RegistrationFlow;
import utility.DataProviderClass;
import utility.TestDataGenerator;

public class RegistrationTest extends BaseClass {
	WebDriver driver;

	@Test(enabled = true, groups = "Sanity")
	public void verifyAccountRegistationFunctionality(Method method) {
		driver = DriverFactory.getDriver();
		
		HomeFlow homeFlow = new HomeFlow(driver);
		RegistrationFlow registrationFlow = new RegistrationFlow(driver);
		homeFlow.clickOnMyAccountLink();
		homeFlow.clickOnRegisterLink();
		registrationFlow.completeRegistration(TestDataGenerator.getFirstName(), TestDataGenerator.getLastName(),
				TestDataGenerator.getEmail(), TestDataGenerator.getPhone(), TestDataGenerator.getPassword());
		registrationFlow.verifyRegistationIsSuccefullyDoneOrNot("Your Account Has Been Created!");
	}
	
	@Test(enabled = true, groups = {"Sanity"})
	public void testRegisterForRetry(Method method) {
		driver = DriverFactory.getDriver();
		
		HomeFlow homeFlow = new HomeFlow(driver);
		RegistrationFlow registrationFlow = new RegistrationFlow(driver);
		homeFlow.clickOnMyAccountLink();
		homeFlow.clickOnRegisterLink();
		 Assert.assertTrue(true);
	}
	
	@Test(dataProvider = "RegistrationData", dataProviderClass = DataProviderClass.class, enabled = true, groups = "DataDriven")
	public void verifyAccountRegistationFunctionalityWithDataDriven(Method method,
			String firstName, String lastName, String emailId, String phoneNo, String password) {
		driver = DriverFactory.getDriver();
		
		HomeFlow homeFlow = new HomeFlow(driver);
		RegistrationFlow registrationFlow = new RegistrationFlow(driver);
		homeFlow.clickOnMyAccountLink();
		homeFlow.clickOnRegisterLink();
		registrationFlow.completeRegistration(firstName, lastName, emailId, phoneNo, password);
		registrationFlow.verifyRegistationIsSuccefullyDoneOrNot("Your Account Has Been Created!");
	}
	
}
