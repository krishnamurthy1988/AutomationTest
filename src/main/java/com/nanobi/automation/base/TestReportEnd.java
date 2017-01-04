package com.nanobi.automation.base;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.testng.annotations.Test;
import com.nanobi.automation.reports.ReportWriter;
import com.nanobi.automation.utils.TestUtilities;


public class TestReportEnd {
	
	
	@Test(priority=0)
	public void reportDispatcher() throws AddressException, MessagingException{
		TestUtilities util = new TestUtilities();
		//util.sendMail("/home/dev/JK_dataFiles/POM_Framework/index.html", "Automation Test Report for Liquid Data Platform version 1.7");
	}
	
	@Test(priority=1)
	public void reportClose() throws IOException
	{
		ReportWriter reportWriter = new ReportWriter();
		reportWriter.endSuite();
	}
	
}
