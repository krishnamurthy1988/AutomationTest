package com.nanobi.automation.base;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nanobi.automation.reports.ReportWriter;

public class TestReportStart {
	
	@Test
	public void reportGenerator() throws IOException
	{
		ReportWriter reportWriter = new ReportWriter();
		reportWriter.startTesting();
		reportWriter.startSuite("Github_Automation_Test_Suite");
	}

}
