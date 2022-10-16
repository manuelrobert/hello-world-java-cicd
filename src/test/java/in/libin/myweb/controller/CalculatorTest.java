package in.javahome.myweb.controller;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CalculatorTest extends TestCase {
	Calculator cal = new Calculator();

	public void testAdd() {
		Properties prop = new Properties();
		
		OutputStream output = null;

		try
		{

			output = new FileOutputStream("../config.properties");

			// set the properties value
			prop.setProperty("database", "localhost");
			prop.setProperty("dbuser", "javaapp");
			prop.setProperty("dbpassword", "passwordme");

			// save properties to project root folder
			prop.store(output, null);

		}catch(
		IOException io)
		{
			io.printStackTrace();
		}finally
		{
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void testMultiply() {
		//Assert.assertEquals(cal.multiply(10, 20), 200);
		int m = 10+10;
		System.out.println(m);
	}
	
	public void testAddNew() {
		//Assert.assertEquals(cal.add(10, 20), 30);
		string temp = "hello";
		System.out.println(temp);
	}
}
