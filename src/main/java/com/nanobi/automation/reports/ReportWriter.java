package com.nanobi.automation.reports;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import com.nanobi.automation.utils.Resources;


public class ReportWriter {

	public static int scriptNumber=1;
	public static String indexResultFilename;
	public static String currentDir;
	public static String currentSuiteName;
	public static int tcid;
	public static Properties OR;
	//public static String currentSuitePath;
	
	public static double passNumber;
	public static double failNumber;
	public static boolean newTest=true;
	public static ArrayList<String> description=new ArrayList<String>();
	public static ArrayList<String> keyword=new ArrayList<String>();
	public static ArrayList<String> teststatus=new ArrayList<String>();
	public static ArrayList<String> timeinmillsec=new ArrayList<String>();
	public static ArrayList<String> screenShotPath=new ArrayList<String>();
	
	
  
	public static void main(String[] args) throws IOException {
		ReportWriter rw = new ReportWriter();
		rw.startTesting();
	}


	public static void startTesting() throws IOException
	  {
		File newFile = null;
		
		 indexResultFilename = Resources.getProperties("Report_Path");


		 currentDir = indexResultFilename.substring(0,indexResultFilename.lastIndexOf("/"));
		// String currentDir = "";
	
	
		 newFile = new File(currentDir);
		 newFile.mkdir();
		// FileInputStream fs;
		// fs= new FileInputStream(System.getenv("NB_HOME")+"\\NanobiTesting_Demo_Server\\Properties\\Demo_Object_Repo.properties");
		// OR.load(fs);
		 
		 String BrowserName="Chrome";
		 
		
		 FileWriter fstream =null;
		 BufferedWriter out =null;
	      try{
	    // Create file 
	   
	     fstream = new FileWriter(indexResultFilename);
	     out = new BufferedWriter(fstream);

         String RUN_DATE = "";
         String testurl = "https://github.com/";
	    
	     String ENVIRONMENT = "Development";//SeleniumServerTest.ConfigurationMap.getProperty("environment");
	     String RELEASE = "version 1.7";//SeleniumServerTest.ConfigurationMap.getProperty("release");
	    
	     out.newLine();
	  
	     out.write("<html>\n");
	     out.write("<HEAD>\n");
	     out.write(" <TITLE>Automation Test Results</TITLE>\n");
	     out.write("</HEAD>\n");
	     
	     out.write("<body>\n");
	     out.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\n");
	     out.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
	     out.write("<tr>\n");

	     out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Test Details :</u></h4>\n");
	     
	     out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></td>\n");
	     out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+RUN_DATE+"</b></td>\n");
	     out.write("</tr>\n");
	     
	     out.write("<tr>\n");
	     out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run StartTime</b></td>\n");
	     out.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+"Time to be added"+"</b></td>\n");
	     out.write("</tr>\n");
	     
	     out.write("<tr>\n");  
	     out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Browser Name</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+BrowserName+"</b></td>\n");
	     out.write("</tr>\n");
	     
	     out.write("<tr>\n");  
	     out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Application URL</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+testurl+"</b></td>\n");
	     out.write("</tr>\n");
	     
	     out.write("<tr>\n");  
	     out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run EndTime</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>END_TIME</b></td>\n");
	     out.write("</tr>\n");
	     
	     out.write("<tr>\n"); 
	     out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Environment</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+ENVIRONMENT+"</b></td>\n");
	     out.write("</tr>\n");
	     out.write("<tr>\n");
	           
	     out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Release</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+RELEASE+"</b></td>\n");
	     out.write("</tr>\n");

	     out.write("</table>\n");
	     
	     out.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
	     out.write("<tr>\n");

	     out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Test Summary :</u></h4>\n");
	     out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Number of Test Cases</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>TOTAL</b></td>\n");
	     out.write("</tr>\n");
	     out.write("<tr>\n");
	           
	     out.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Number of Test Cases Pass</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>PASS</b></td>\n");
	   
	     out.write("</tr>\n");
	     out.write("<tr>\n");

	     out.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Number of Test Cases Fail</b></td>\n");
	     out.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>FAIL</b></td>\n");
	     out.write("</tr>\n");
	     out.write("<tr>\n");

	     out.write("</table>\n");
	     
	    //Close the output stream
	    out.close();
	    }catch (Exception e){//Catch exception if any
	      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
		    newFile = null;
	    }
	  }
	
    public static void startSuite(String suiteName){

	    FileWriter fstream =null;
		BufferedWriter out =null;
		//currentSuiteName = suiteName.replaceAll(" ", "_");
		tcid=1;
	    try{
	    	// build the suite folder
	    //	currentSuitePath = currentDir; //+"//"+suiteName.replaceAll(" ","_");
	    //	currentSuiteDir = suiteName.replaceAll(" ","_");
	    //	File f = new File(currentSuitePath);
		//	f.mkdirs();
	    	

	    fstream = new FileWriter(indexResultFilename,true);
	  	out = new BufferedWriter(fstream);
	      
    	out.write("<h4> <FONT COLOR=660000 FACE= Arial  SIZE=4.5> <u>"+suiteName+" Report :</u></h4>\n");
        out.write("<table  border=1 cellspacing=1 cellpadding=1 width=100%>\n");
    	out.write("<tr>\n");
        out.write("<td width=10%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Script#</b></td>\n");
        out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Scenario Name</b></td>\n");
        out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Scenario Description</b></td>\n");
        out.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>\n");
        out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run Start Time</b></td>\n");
        out.write("<td width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run End Time</b></td>\n");
        out.write("</tr>\n");
        out.close();
	    }catch(Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }
	}
    
    public void endSuite(){
    	 FileWriter fstream =null;
 		BufferedWriter out =null;
 		
 	    try{
 	    fstream = new FileWriter(indexResultFilename,true);
 	  	out = new BufferedWriter(fstream);
        out.write("</table>\n");
        out.close();
 	    }catch(Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }

    }
	
	public void addTestCaseToReport(String testCaseName, String scenarioName, String testCaseStartTime,String testCaseEndTime,String status){
		newTest = true;
		FileWriter fstream = null;
		BufferedWriter out = null;
		indexResultFilename = Resources.getProperties("Report_Path");

		try {
			newTest = true;
			// build the keywords page

			fstream = new FileWriter(indexResultFilename, true);
			out = new BufferedWriter(fstream);
			
			// out.newLine();

			out.write("<tr>\n");
			// System.out.println(currentSuitePath);
			out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + scriptNumber
					+ "</b></td>\n");
			if (status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip")) {

				out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseName
						+ "</b></td>\n");
				out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + scenarioName
						+ "</b></td>\n");
			} else
				// out.write("<td width=40% align= center ><FONT COLOR=#153E7E
				// FACE= Arial SIZE=2><b><a
				// href=file:///"+currentDir+"//"+currentSuiteName+"_TC"+tcid+"_"+testCaseName.replaceAll("
				// ", "_")+".html>"+testCaseName+"</a></b></td>\n");
				out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseName
						+ "</b></td>\n");
			out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + scenarioName
					+ "</b></td>\n");
			tcid++;
			if (status.startsWith("Pass"))
				out.write("<td width=10% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"
						+ status + "</b></td>\n");
			else if (status.startsWith("Fail"))
				out.write("<td width=10% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"
						+ status + "</b></td>\n");
			else if (status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
				out.write("<td width=10% align= center  bgcolor=yellow><FONT COLOR=153E7E FACE=Arial SIZE=2><b>"
						+ status + "</b></td>\n");

			out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseStartTime
					+ "</b></td>\n");
			out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>" + testCaseEndTime
					+ "</b></td>\n");

			out.write("</tr>\n");
			
			

			scriptNumber++;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	public static void updateEndTime(String endTime) {
		StringBuffer buf = new StringBuffer();
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(indexResultFilename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		        BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    
		    //Read File Line By Line
		    
		    while ((strLine = br.readLine()) != null)   {
		    	
		     if(strLine.indexOf("END_TIME") !=-1){
		    	 strLine=strLine.replace("END_TIME", endTime);
		     }
		     buf.append(strLine);
		     
		    }
		  //Close the input stream
		    in.close();
		  //  System.out.println(buf);
		    FileOutputStream fos=new FileOutputStream(indexResultFilename);
			 DataOutputStream   output = new DataOutputStream (fos);	 
	    	 output.writeBytes(buf.toString());
	    	 fos.close();
		    
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
		
	}
	
	
	public static void noOfTC(int no_TC) {
		StringBuffer buf = new StringBuffer();
		String total = String.valueOf(no_TC);
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(indexResultFilename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		        BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    
		    //Read File Line By Line
		    
		    while ((strLine = br.readLine()) != null)   {
		    	
		     if(strLine.indexOf("TOTAL") !=-1){
		    	 strLine=strLine.replace("TOTAL", total);
		     }
		     buf.append(strLine);
		     
		    }
		    
		  //Close the input stream
		    in.close();
		  //  System.out.println(buf);
		    FileOutputStream fos=new FileOutputStream(indexResultFilename);
			 DataOutputStream   output = new DataOutputStream (fos);	 
	    	 output.writeBytes(buf.toString());
	    	 fos.close();
		    
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}	
	
	
		public static void noOfTCPass(int no_TC_Pass) {
			StringBuffer buf = new StringBuffer();
			String pass = String.valueOf(no_TC_Pass);
			try{
			    // Open the file that is the first 
			    // command line parameter
			    FileInputStream fstream = new FileInputStream(indexResultFilename);
			    // Get the object of DataInputStream
			    DataInputStream in = new DataInputStream(fstream);
			        BufferedReader br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    
			    //Read File Line By Line
			    
			    while ((strLine = br.readLine()) != null)   {
			    	
			     if(strLine.indexOf("PASS") !=-1){
			    	 strLine=strLine.replace("PASS", pass);
			     }
			     buf.append(strLine);
			     
			    }
			    
			  //Close the input stream
			    in.close();
			  //  System.out.println(buf);
			    FileOutputStream fos=new FileOutputStream(indexResultFilename);
				 DataOutputStream   output = new DataOutputStream (fos);	 
		    	 output.writeBytes(buf.toString());
		    	 fos.close();
			    
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
		
	}
	
		public static void noOfTCFail(int no_TC_Fail) {
			StringBuffer buf = new StringBuffer();
			String fail = String.valueOf(no_TC_Fail);
			try{
			    // Open the file that is the first 
			    // command line parameter
			    FileInputStream fstream = new FileInputStream(indexResultFilename);
			    // Get the object of DataInputStream
			    DataInputStream in = new DataInputStream(fstream);
			        BufferedReader br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    //Read File Line By Line
			    
			    while ((strLine = br.readLine()) != null)   {
			    	
			     if(strLine.indexOf("FAIL") !=-1){
			    	 strLine=strLine.replace("FAIL", fail);
			     }
			     buf.append(strLine);
			     
			    }
			    
			  //Close the input stream
			    in.close();
			  //  System.out.println(buf);
			    FileOutputStream fos=new FileOutputStream(indexResultFilename);
				 DataOutputStream   output = new DataOutputStream (fos);	 
		    	 output.writeBytes(buf.toString());
		    	 fos.close();
			    
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
		}	

}
