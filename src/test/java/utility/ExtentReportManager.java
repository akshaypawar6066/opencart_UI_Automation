package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	 private static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            extent = createInstance();
	        }
	        return extent;
	    }

	    public static ExtentReports createInstance() {
	    	String timeStamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.SS").format(new Date());
	    	String reportName= "Test-Report "+ timeStamp +".html";
	        String reportPath = System.getProperty("user.dir") + "/reports/"+ reportName;

	        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	        spark.config().setDocumentTitle("OpenCart Automation Report");
	        spark.config().setReportName("Open Cart Functional Testing:");
	        spark.config().setTheme(Theme.DARK);
	       

	        ExtentReports extent = new ExtentReports();
	        extent.setSystemInfo("Application", "OpenCart");
	        extent.setSystemInfo("Module", "Admin");
	        extent.setSystemInfo("SubModule", "Customers");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("UserName", System.getProperty("user.name"));
	        /*
	        
	        extent.setSystemInfo("Browser", System.getProperty("browser", "Chrome"));
	        extent.setSystemInfo("OS", System.getProperty("os.name"));
	        */


	        
	        extent.attachReporter(spark);

	        return extent;
	    }
}
