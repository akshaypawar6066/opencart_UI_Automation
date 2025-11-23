package utility;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="RegistrationData")
	public Object[][] getRegistractionData() {
		int rows=3;
		
		Object [][] data = new Object[rows][5];
		
		for(int i=0; i<rows;i++) {
			data[i][0]=TestDataGenerator.getFirstName();
			data[i][1] = TestDataGenerator.getLastName();
			data[i][2] = TestDataGenerator.getEmail();
			data[i][3]= TestDataGenerator.getPhone();
			data[i][4] = TestDataGenerator.getPassword();
		}
		
		
		return data;
		
	}
	
	
	@DataProvider(name="InValidLoginData")
	public Object[][] getLoginData() {
	  Object[][] loginData={
		  {ConfigReader.get("app.invalidEmail"), ConfigReader.get("app.invalidPassword")},
		  {ConfigReader.get("app.emailId"), ConfigReader.get("app.invalidPassword")},
		  {ConfigReader.get("app.invalidEmail"), ConfigReader.get("app.password")}
	  };
	  
	  return loginData;
		
	}

}
