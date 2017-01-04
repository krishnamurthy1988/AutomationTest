package com.nanobi.automation.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GithubLoginPage {
	public static Logger APPICATION_LOGS=Logger.getLogger(GithubLoginPage.class.getName());
	
	@FindBy(how=How.ID,using="login_field")
	@CacheLookup
	private WebElement usernameTextBox;
	
	@FindBy(how=How.ID,using="password")
	@CacheLookup
	private WebElement passwordTextBox;
	
	@FindBy(how=How.XPATH,using="//div[@class='site-header-actions']/a[@class='btn site-header-actions-btn mr-1']")
	@CacheLookup
	private WebElement signInBtn;
	
	@FindBy(how=How.XPATH,using="//div[@class='auth-form-body mt-3']/input[@class='btn btn-primary btn-block']")
	@CacheLookup
	private WebElement loginSignInBtn;
	
	
	@FindBy(how=How.XPATH,using="//div[contains(text(),'System Admin')]")
	@CacheLookup
	private WebElement clickBtn;
	
	@FindBy(how=How.XPATH,using="html/body/div[2]/div/ul[1]/li[1]/a")
	@CacheLookup
	private WebElement successMsgTxt;
	
	@FindBy(how=How.XPATH,using="//*[@id='js-flash-container']/div/div")
	@CacheLookup
	private WebElement failureMsgTxt;
	
	@FindBy(how=How.XPATH,using="//ul[@id='user-links']/li[3]/a[@class='header-nav-link name tooltipped tooltipped-sw js-menu-target']")
	@CacheLookup
	private WebElement viewProfileButton;
	
	@FindBy(how=How.XPATH,using="//form[@class='logout-form']/button[@class='dropdown-item dropdown-signout']")
	@CacheLookup
	private WebElement signOutButton;
	
	public boolean verifySuccesfullSignIn(String username, String password, String expectedText) throws InterruptedException {
		try {
			clickOnSignIn();
			enterUserName(username);
			enterPassword(password);
			clickOnLoginSignIn();
			String result = verifySuccesfullSignIn(expectedText);
			clickOnLogout();
			if(result.equalsIgnoreCase("Pass")){
				return true;
			}
			APPICATION_LOGS.info("executing verify SignIn function.");
		} catch (Exception ex) {
			APPICATION_LOGS.error("Executing verifySignIn function failed : " + ex);
			return false;
		}
		//return getSuccessMessage().contains("Home");
		return false;
	}
	
	public boolean verifyFailedSignIn(String username, String password, String Expected) throws InterruptedException {
		try {
			String result = null;
			clickOnSignIn();
			enterUserName(username);
			enterPassword(password);
			clickOnLoginSignIn();
			result = verifyFailedSignIn(Expected);
			if(result.equalsIgnoreCase("Pass")){
				return true;
			}
			APPICATION_LOGS.info("executing verify SignIn function.");
		} catch (Exception ex) {
			APPICATION_LOGS.error("Executing verifySignIn function failed : " + ex);
			return false;
		}
		//return getSuccessMessage().contains("Home");
		return false;
	}
	
	public void enterUserName(String userName) {
		try{
			if(usernameTextBox.isDisplayed()&&usernameTextBox.isEnabled())
				usernameTextBox.sendKeys(userName);
			APPICATION_LOGS.info("Inputting username : "+userName);
		}catch(Exception ex){
			APPICATION_LOGS.error(ex);
		}
		
	}
	
	public void enterPassword(String password) {
		try{
			if(passwordTextBox.isDisplayed()&&passwordTextBox.isEnabled())
				passwordTextBox.sendKeys(password);
			APPICATION_LOGS.info("Inputting password : "+password);
		}catch(Exception ex){
			APPICATION_LOGS.error(ex);
		}
		
	}
	
	public void clickOnSignIn() {
		try{
			if(signInBtn.isDisplayed()&&signInBtn.isEnabled())
				signInBtn.click();
			APPICATION_LOGS.info("Clicking on Sign In Button : PASS");
		}catch(Exception ex){
			APPICATION_LOGS.error("Clicking on Sign In Button : FAILED"+ex);
		}
		
	}
	
	public void clickOnLoginSignIn() {
		try {
			if (loginSignInBtn.isDisplayed() && loginSignInBtn.isEnabled())
				loginSignInBtn.click();
			APPICATION_LOGS.info("Clicking on Sign In Button : PASS");
		} catch (Exception ex) {
			APPICATION_LOGS.error("Clicking on Sign In Button : FAILED" + ex);
		}

	}
	
	public void clickOnLogout() {
		try {
			if (viewProfileButton.isDisplayed() && viewProfileButton.isEnabled()){
				viewProfileButton.click();
			}
			if (signOutButton.isDisplayed() && signOutButton.isEnabled())
				signOutButton.click();
			APPICATION_LOGS.info("Clicking on View Profile Button : PASS");
		} catch (Exception ex) {
			APPICATION_LOGS.error("Clicking on View Profile Button : FAILED" + ex);
		}

	}
	
	public String verifyFailedSignIn(String ExpectedText) {
		String actualText = null;
		try {
			if (failureMsgTxt.isDisplayed() && failureMsgTxt.isEnabled())
				actualText = failureMsgTxt.getText();
			if(actualText.equals(ExpectedText)){
				APPICATION_LOGS.info("Verifying failed Sign In : PASS");
				return "Pass";
			}		
		} catch (Exception ex) {
			APPICATION_LOGS.error("Verifying failed Sign In : FAILED" + ex);
			return "False";	
		}
		return "False";
	}
	
	public String verifySuccesfullSignIn(String ExpectedText) {
		String actualText = null;
		
		try{
			if(successMsgTxt.isDisplayed()&&successMsgTxt.isEnabled())
				actualText = successMsgTxt.getText();
			if(actualText.trim().equals(ExpectedText.trim())){
				APPICATION_LOGS.info("Verifying failed Sign In : PASS");
				return "Pass";
			}
		}catch(Exception ex){
			APPICATION_LOGS.error("Verifying Successfull Sign In : FAILED" + ex);
			return "Fail";
		}
		return "Fail";
	}

}
