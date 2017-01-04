package com.nanobi.automation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Resources {
	// static Logger logger = Logger.getLogger("AleretPlugin-->Resources");

	// private static ResourceBundle content_server_prop = null;
	static boolean isLocalPathEnable = false;

	static String baseDir = System.getenv("NB_HOME");

	static String fileSep = System.getProperty("file.separator");

	static Properties properties = new Properties();

	static FileInputStream fis = null;

	static {
		if (baseDir != null) {
			try {
				fis = new FileInputStream(baseDir + fileSep + "automation_test_configuration.properties");
				isLocalPathEnable = true;
			} catch (FileNotFoundException e) {
				isLocalPathEnable = false;
				// logger.warn("Config File not found in NB_HOME");
			}
		} else {
			// logger.warn("NB_HOME not set");
		}
		if (!isLocalPathEnable) {
			try {
				// logger.info("Trying in Current Working Directory:" +
				// file.getAbsolutePath());
				fis = new FileInputStream("nanoclientapplication.properties");

			} catch (FileNotFoundException e) {
				// logger.error("Configration not Found in Current Working
				// Directory");
			}
		}

	}

	/**
	 * Returns a value from properties file as <code>String</code>
	 * <p>
	 * 
	 * The <code>getProperties</code> method for class <code>Resources</code>
	 * returns a string consisting of the value of key property passed as
	 * Argument in method.
	 * 
	 * @return value corresponds to key in properties file
	 */
	public static String getProperties(String property_name) {

		String config = null;

		try {
			properties.load(fis);
		} catch (IOException e) {
			// logger.error(e);
		}
		config = properties.getProperty(property_name);
		return config;
	}

	public static String readFileFromResource(InputStream inputStream) {
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} catch (UnsupportedEncodingException e) {
			// logger.info(e);
		} catch (IOException e) {
			// logger.info(e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// logger.info(e);
			}
		}
		// System.out.println(writer.toString());
		return writer.toString();
	}

}
