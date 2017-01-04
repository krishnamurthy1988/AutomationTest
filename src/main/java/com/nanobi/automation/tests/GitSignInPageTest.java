package com.nanobi.automation.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.nanobi.automation.base.TestBaseSetup;
import com.nanobi.automation.datatablereader.ExcelReader;
import com.nanobi.automation.pageobjects.GithubLoginPage;
import com.nanobi.automation.reports.ReportWriter;
import com.nanobi.automation.utils.TestUtilities;


public class GitSignInPageTest extends TestBaseSetup{
	
	public static Logger APPICATION_LOGS = Logger.getLogger(GitSignInPageTest.class.getName());
	private WebDriver driver;
	public GithubLoginPage signInPage;
	public ReportWriter reportWriter;
	public ExcelReader xlreader;

	@BeforeClass
	public void setUp() throws IOException {
		driver = getDriver();
		xlreader = getExcelReader();

	}
	
	@Test(priority = 0)
	public void verifySuccessSignInFunction() throws InterruptedException {
		String scenarioName = null;
		String scenarioDescription = null;
		String Reports_End_Time = null;
		String Reports_Start_Time = null;
		String userName = null;
		String password = null;
		boolean flag = false;
		String expectedText = "   Pull requests ";
		reportWriter = new ReportWriter();
		APPICATION_LOGS.info("Verifying Sign In functionality.");
		Reports_Start_Time = TestUtilities.starTime("dd-MMMMM-yyyy hh:mm:ss:aaa");
		try {
			signInPage = PageFactory.initElements(driver, GithubLoginPage.class);
			scenarioName = "Verify the Successful login to GitHub.";
			scenarioDescription = "Verify the Successful login to GitHub, Given a valid GitHub username and password";
			userName = "krishnamurthy1988";
			password = "Welcome$123";
			Reports_End_Time = TestUtilities.endTime("dd-MMMMM-yyyy hh:mm:ss:aaa");
			flag = signInPage.verifySuccesfullSignIn(userName, password, expectedText);
			if (flag) {
				reportWriter.addTestCaseToReport(scenarioName, scenarioDescription, Reports_Start_Time,
						Reports_End_Time, "Pass");
			} else {
				reportWriter.addTestCaseToReport(scenarioName, scenarioDescription, Reports_Start_Time,
						Reports_End_Time, "Fail");
			}

		} catch (Exception ex) {
			reportWriter.addTestCaseToReport(scenarioName, scenarioDescription, Reports_Start_Time, Reports_End_Time,
					"Fail");
			APPICATION_LOGS.error("Verifying SignIn functionality FAILED " + ex);

		}
	}
	
	@Test(priority = 1)
	public void verifyFailedSignInFunction() throws InterruptedException {
		String scenarioName = null;
		String scenarioDescription = null;
		String Reports_End_Time = null;
		String Reports_Start_Time = null;
		String userName = null;
		String password = null;
		String expectedText = "Incorrect username or password.";
		boolean flag = false;
		reportWriter = new ReportWriter();
		APPICATION_LOGS.info("Verifying Sign In functionality.");
		Reports_Start_Time = TestUtilities.starTime("dd-MMMMM-yyyy hh:mm:ss:aaa");
		try {
			signInPage = PageFactory.initElements(driver, GithubLoginPage.class);
			scenarioName = "Verify the Failed login to GitHub";
			scenarioDescription = "Failed login to GitHub, Given a invalid GitHub username and password";
			userName = "krishnamurthy1988";
			password = "Welcome$1234";
			Reports_End_Time = TestUtilities.endTime("dd-MMMMM-yyyy hh:mm:ss:aaa");
			flag = signInPage.verifyFailedSignIn(userName, password, expectedText);
			if (flag) {
				reportWriter.addTestCaseToReport(scenarioName, scenarioDescription, Reports_Start_Time,
						Reports_End_Time, "Pass");
			} else {
				reportWriter.addTestCaseToReport(scenarioName, scenarioDescription, Reports_Start_Time,
						Reports_End_Time, "Fail");
			}
		} catch (Exception ex) {
			reportWriter.addTestCaseToReport(scenarioName, scenarioDescription, Reports_Start_Time, Reports_End_Time,
					"Fail");
			APPICATION_LOGS.error("Verifying SignIn functionality FAILED " + ex);

		}
	}
	
}
