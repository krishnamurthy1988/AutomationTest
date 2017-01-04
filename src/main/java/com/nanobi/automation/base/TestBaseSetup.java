package com.nanobi.automation.base;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.nanobi.automation.datatablereader.ExcelReader;
import com.nanobi.automation.reports.ReportWriter;
import com.nanobi.automation.utils.Resources;



public class TestBaseSetup {
	public static Logger APPICATION_LOGS = Logger.getLogger(TestBaseSetup.class.getName());
	
	
	public static WebDriver driver = null;
	static String driverPath = Resources.getProperties("chrome_driver");

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(int browserType, String appURL) {
		switch (browserType) {
		case 1:
			driver = initChromeDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initChromeDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		try {
			System.out.println("Launching google chrome with new profile..");
			System.setProperty("webdriver.chrome.driver", driverPath);
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			caps.setCapability("acceptSslCerts", false);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(caps);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(appURL);
		} catch (Exception ex) {
			APPICATION_LOGS.error("Chrome drive not found or not executed : " + ex);
		}
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(int browserType, String appURL) {
		try {
            
			setDriver(browserType, appURL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		ReportWriter reportWriter = new ReportWriter();
		driver.quit();
	}

	public ExcelReader getExcelReader() {
		String excel_FilePath = Resources.getProperties("Test_Case_Controller");
		ExcelReader reader = new ExcelReader(excel_FilePath);
		return reader;

	}
	
	
}
